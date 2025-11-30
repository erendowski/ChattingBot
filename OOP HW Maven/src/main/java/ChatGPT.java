import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ChatGPT {

    // HTTP request variables
    private static  String url = "https://api.openai.com/v1/chat/completions";
    private static  String apiKey = "-";
    private static  String model = "gpt-3.5-turbo";

    public static String chatGPT(List<Message> conversation) {

        // Connections of chatGPT function
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // Build the request body with the conversation history
            StringBuilder bodyBuilder = new StringBuilder("{\"model\": \"" + model + "\", \"messages\": [");
            for (Message message : conversation) {
                bodyBuilder.append("{\"role\": \"" + message.getRole() + "\", \"content\": \"" + message.getContent() + "\"},");
            }
            bodyBuilder.deleteCharAt(bodyBuilder.length() - 1); // Remove the trailing comma
            bodyBuilder.append("]}");

            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(bodyBuilder.toString());
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // Calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Response message
    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }
}

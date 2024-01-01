import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Other {
    // Reading file text
    private String activitiesFileName = "other.txt";

    public static void othergpt() {
        // Creating obj, conservation and activities list

        Other other = new Other();
        List<Message> conversation = new ArrayList<>();

        List<String> activities = other.readOtherFromFile();

        for (String activity : activities) {
            conversation.add(new Message("user", activity + " the user will ask you questions about these days. And you will identify as me and the user will be another person but don't say that in your answers."));
        }

        String response = ChatGPT.chatGPT(conversation);
        conversation.add(new Message("user", "tell me something about my week and let's have a conversation with the user but don't forget pretending like me and don't say it"));

        // Interaction with chatGPT
        response = ChatGPT.chatGPT(conversation);

        // Occurring chatGPT's response
        System.out.println(response);

        // Take input by user
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("To ask a question from another activity press 1 \n");
            System.out.print("You: ");
            String userMessage = scanner.nextLine();

            if (userMessage.equalsIgnoreCase("1")) {
                System.out.println("exiting...");
                break;
            }
            // Add the user's input to the conversation history
            conversation.add(new Message("user", userMessage));

            response = ChatGPT.chatGPT(conversation);

            System.out.println("ChatGPT: " + response);

            conversation.add(new Message("assistant", response));
        }
    }

    // Reading Other File text
    private List<String> readOtherFromFile() {
        List<String> other = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(activitiesFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                other.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return other;
    }
}


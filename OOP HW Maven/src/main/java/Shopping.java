import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Shopping {
    // Reading file text
    private String activitiesFileName = "shopping.txt";

    public static void shoppinggpt() {
        // Creating obj, conservation and activities list

        Shopping shopping = new Shopping();
        List<Message> conversation = new ArrayList<>();

        List<String> activities = shopping.readShoppingFromFile();

        for (String activity : activities) {
            conversation.add(new Message("user", activity + " the user will ask you questions about these days. and you will pretend like me and the user will be another person but don't say that in your answers."));
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

    // Reading Shopping File text func

    private List<String> readShoppingFromFile() {
        List<String> shopping = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(activitiesFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                shopping.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shopping;
    }
}


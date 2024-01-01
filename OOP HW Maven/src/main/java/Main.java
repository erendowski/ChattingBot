import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user name: ");
        String name = scanner.nextLine();
        System.out.println(greet(name));

        //The Desired question format is returned with a loop
        while (true) {
            System.out.println("-> Choose an activity to get to know me.");
            System.out.println("    - Game");
            System.out.println("    - Sport");
            System.out.println("    - Entertainment");
            System.out.println("    - Food");
            System.out.println("    - Shopping");
            System.out.println("    - Lessons");
            System.out.println("    - Other");
            String userInput =scanner.nextLine();
            getPreferredActivity(userInput);
        }
    }

    //Greeting for user
    public static String greet(String name) {

        return "Hello " + name + "! Let's get to know each other.";
    }

    //Switch-case function
    public static void getPreferredActivity(String userInput) {
        switch (userInput.toLowerCase()) {
            case "game":

                Game.gamegpt();
                
                break;

            case "sport":

                Sport.sportgpt();

                break;

            case "entertainment":

                Entertainment.entertainmentgpt();

                break;

            case "food":

                Food.foodgpt();

                break;

            case "shopping":

                Shopping.shoppinggpt();

                break;

            case "lessons":

                Lesson.lessongpt();

                break;

            case "other":

                Other.othergpt();

                break;

            default:
                System.out.println("I'm sorry, I don't understand that option.\n");
                break;
        }
    }
}
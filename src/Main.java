import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    static QuestionGenerator questionGenerator = new QuestionGenerator();
    static final DecimalFormat df = new DecimalFormat("0.00");
    static int playerScorePartOne = 0;
    static int playerScorePartTwo = 0;
    static boolean gamePlaying = true;
    static Scanner userEntry = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Math game");
        System.out.println("Before we begin, please enter your first name:");
        String firstName = userEntry.nextLine();
        System.out.println("Now please enter your surname:");
        String secondName = userEntry.nextLine();

        System.out.printf("Welcome %s %s, we will now begin with the first 10 questions\n", firstName, secondName);
        playGame();
    }

    private static void playGame() {

        while(gamePlaying ==true) {
            // easy mode - at least two questions of each kind
            playEasyMode();
            System.out.println("Stage one complete - player scored " + playerScorePartOne + " out of 10.");
            System.out.println("Now entering stage two, challenge mode");
            playHardMode();
            generateReport();
            exitGame();
        }
    }

    private static int playHardMode() {
        for(int i = 1; i <= 20; i++) {
            questionGenerator.hardMode = true;
            playerScorePartTwo = questionGenerator.generateQuestion(userEntry, playerScorePartTwo);
        }

        return playerScorePartTwo;
    }

    /**
     * Easy mode - 10 questions in total, at least two questions of each operator type
     */
    private static void playEasyMode() {
        for(int i = 1; i <= 10; i++) {
            switch(i) {
                case 1, 5:
                    playerScorePartOne = questionGenerator.generateQuestion("+", userEntry, playerScorePartOne);
                    break;
                case 2, 6:
                    playerScorePartOne = questionGenerator.generateQuestion("-", userEntry, playerScorePartOne);
                    break;
                case 3, 7:
                    playerScorePartOne = questionGenerator.generateQuestion("*", userEntry, playerScorePartOne);
                    break;
                case 4, 8:
                    playerScorePartOne = questionGenerator.generateQuestion("/", userEntry, playerScorePartOne);
                    break;
                default:
                    playerScorePartOne = questionGenerator.generateQuestion("+", userEntry, playerScorePartOne);
            }
        }
    }

    private static void generateReport() {

        System.out.println("Player has scored " + playerScorePartOne + " in the first section.");
        System.out.println("Player has scored " + playerScorePartTwo + " in the second section.");
        int totalScore = playerScorePartOne + playerScorePartTwo;
        if(totalScore <= 4) {
            System.out.println("Player has failed the test.");
        } else if(totalScore>=5 && totalScore<=10) {
            System.out.println("Player has achieved a merit.");
        } else if(totalScore>=11 && totalScore<=16) {
            System.out.println("Player has achieved a merit.");
        } else if(totalScore>=17) {
            System.out.println("Player has achieved a distinction.");
        }
    }

    private static void exitGame() {
        System.out.printf("Thank you for playing.");
        System.exit(0);
    }
}
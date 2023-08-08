import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.round;

public class QuestionGenerator {

    boolean hardMode = false;
    int lowerNumber = 1;
    int higherNumber = 50;
    HashMap<String, String> arithmetic = new HashMap<>();
    Set<Integer> numberSet = new HashSet<>();
    Random random = new Random();

    public QuestionGenerator() {
        arithmetic.put("add", "+");
        arithmetic.put("subtract", "-");
        arithmetic.put("multiply", "*");
        arithmetic.put("divide", "/");
    }
    public int generateQuestion(Scanner userEntry, int playerScore) {
        if(hardMode==false) {
            higherNumber=25;
        } else {
            higherNumber=50;
        }
        int firstNum = random.nextInt(higherNumber+1);
        int secondNum = random.nextInt(higherNumber+1);
        String operator = generateRandomOperator();

        System.out.printf("What is %d %s %d?", firstNum, operator, secondNum);
        userEntry.reset();
        String userAnswer = userEntry.nextLine();

        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher matcher = pattern.matcher(userAnswer);
        boolean isValid = matcher.matches();
        if(isValid==false) {
            System.out.println("Please provide your answer in digits instead of words (i.e, 12.44, 1.2, 12 etc)");
            generateQuestion(userEntry, playerScore);
        }
        float answer = calculateAnswer(firstNum, secondNum, operator);
        float userAnswerDigit = Float.valueOf(userAnswer);
        System.out.println(userAnswerDigit);
        System.out.println("user answer = " + userAnswerDigit + ". Real answer = " + answer);

        if(userAnswerDigit==answer) {
            System.out.println("Player correct");
            playerScore++;
        }
        return playerScore;
    }

    public int generateQuestion(String operator, Scanner userEntry, int playerScore) {
        if(hardMode==false) {
            higherNumber=25;
        } else {
            higherNumber=50;
        }
        int firstNum = random.nextInt(higherNumber+1);
        int secondNum = random.nextInt(higherNumber+1);

        System.out.printf("What is %d %s %d?", firstNum, operator, secondNum);
        userEntry.reset();
        String userAnswer = userEntry.nextLine();

        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher matcher = pattern.matcher(userAnswer);
        boolean isValid = matcher.matches();
        if(isValid==false) {
            System.out.println("Please provide your answer in digits instead of words (i.e, 12.44, 1.2, 12 etc)");
            generateQuestion(operator, userEntry, playerScore);
        }

        float answer = calculateAnswer(firstNum, secondNum, operator);
        float userAnswerDigit = Float.valueOf(userAnswer);

        System.out.println("user answer = " + userAnswerDigit + ". Real answer = " + answer);

        if(userAnswerDigit==answer) {
            System.out.println("Player correct");
            playerScore++;
        }

        return playerScore;
    }

    private String generateRandomOperator() {
        String[] randomOperators = {"+", "-", "*", "/"};
        int randomIndex = random.nextInt(randomOperators.length);
        return randomOperators[randomIndex];
    }

    private float calculateAnswer(float firstNum, float secondNum, String operator) {
        switch (operator) {
            case "+":
                return firstNum + secondNum;
            case "-":
                return firstNum - secondNum;
            case "*":
                return firstNum * secondNum;
            case "/":
                return firstNum / secondNum;
        }
        return -1;
    }
}

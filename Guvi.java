import java.util.ArrayList;
import java.util.Scanner;

// Class representing a single quiz question
class Question {
    private String text;           // The question text
    private String[] options;      // Array of possible options
    private String correctAnswer;  // The correct answer (case-insensitive)

    // Constructor to initialize a question
    public Question(String text, String[] options, String correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer.toLowerCase(); // Store correct answer in lowercase
    }

    // Getter for question text
    public String getText() {
        return text;
    }

    // Getter for options
    public String[] getOptions() {
        return options;
    }

    // Checks if the provided answer is correct (case-insensitive)
    public boolean isCorrect(String answer) {
        return correctAnswer.equals(answer.toLowerCase());
    }
}

// Class representing the quiz, which can hold multiple questions
class Quiz {
    private ArrayList<Question> questions; // List to store questions

    // Constructor initializes the questions list
    public Quiz() {
        questions = new ArrayList<>();
    }

    // Adds a question to the quiz after validating the correct answer
    public void addQuestion(String text, String[] options, String correctAnswer) {
        boolean validAnswer = false;
        // Check if the correct answer matches any of the provided options
        for (String option : options) {
            if (option.equalsIgnoreCase(correctAnswer)) {
                validAnswer = true;
                break;
            }
        }

        // If the correct answer is not among the options, show error and return
        if (!validAnswer) {
            System.out.println("Error: Correct answer must be one of the provided options.");
            return;
        }

        // Add the question to the list
        questions.add(new Question(text, options, correctAnswer));
        System.out.println("Question added.");
    }

    // Starts the quiz: asks each question and checks the user's answer
    public void startQuiz() {
        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int score = 0; // To keep track of correct answers
        System.out.println("Quiz started!");

        // Loop through all questions
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.getText());
            String[] options = q.getOptions();
            // Display options as A), B), C), etc.
            for (int j = 0; j < options.length; j++) {
                System.out.println((char) ('A' + j) + ") " + options[j]);
            }

            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            // Check if the answer is correct
            if (q.isCorrect(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }

        // Calculate and display the score and percentage
        double percentage = (score * 100.0) / questions.size();
        System.out.printf("Score: %d/%d (%.2f%%)%n", score, questions.size(), percentage);
    }
}

// Main class to run the quiz application
public class Guvi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();

        // Main menu loop
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Question");
            System.out.println("2. Start Quiz");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate menu input
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Question
                    System.out.print("Enter the question text: ");
                    String text = scanner.nextLine();

                    System.out.print("Enter the number of options: ");
                    int numOptions = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    String[] options = new String[numOptions];
                    // Input each option
                    for (int i = 0; i < numOptions; i++) {
                        System.out.print("Enter option " + (char) ('A' + i) + ": ");
                        options[i] = scanner.nextLine();
                    }

                    System.out.print("Enter the correct answer: ");
                    String correctAnswer = scanner.nextLine();

                    quiz.addQuestion(text, options, correctAnswer);
                    break;

                case 2: // Start Quiz
                    quiz.startQuiz();
                    break;

                case 3: // Exit
                    System.out.println("Program ended.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
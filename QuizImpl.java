import java.util.Scanner;

class QuizImpl implements Quizable, QuestionRepository {
    private Question[] questions = null;
    private int currentIndex = 0;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void menu() {
        while (true) {
            gameManager();
        }
    }

    private void gameManager() {
        System.out.println("********Menu********");
        System.out.println("Press '1' to Start Quiz");
        System.out.println("Press '2' to Manage Quiz");
        System.out.println("Press '3' to Quit Quiz");
        System.out.println("********************");

        System.out.print("Enter your choice: ");
        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Only numbers are supported. Please select according to the menu.");
            System.out.println("--------------------------------------------------");
            return;
        }

        switch (choice) {
            case 1:
                startGame();
                break;
            case 2:
                manageGame();
                break;
            case 3:
                quitGame();
                break;
            default:
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid choice. Please select according to the menu.");
                System.out.println("--------------------------------------------------");
                break;
        }
    }

    @Override
    public void startGame() {
        if (currentIndex == 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("Please add questions before starting the quiz in Manage Quiz");
            System.out.println("--------------------------------------------------");
            menu();
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Welcome to the Quiz Game!");
        System.out.println("--------------------------------------------------");

        int totalScore = 0;
        int questionCount = 0;

        int mistakes = 0;
        int maxMistakesAllowed = 5;

        while (questionCount < currentIndex && mistakes < maxMistakesAllowed) {
            Question currentQuestion = questions[questionCount];

            System.out.println("Question " + (questionCount + 1) + ": " + currentQuestion.getQuestionText());

            for (int i = 0; i < currentQuestion.getOptions().length; i++) {
                System.out.println((i + 1) + ". " + currentQuestion.getOptions()[i].getName());
            }

            System.out.print("Enter your answer (1-" + currentQuestion.getOptions().length + ") or press Q to quit: ");
            String input = scanner.nextLine();


            if (input.equalsIgnoreCase("Q")) {
                break;
            }

            try {
                int userAnswer = Integer.parseInt(input);
                if (userAnswer >= 1 && userAnswer <= currentQuestion.getOptions().length) {
                    if (currentQuestion.isCorrectAnswer(userAnswer - 1)) {
                        totalScore++;
                    } else {
                        mistakes++;
                    }
                    questionCount++;
                } else {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Invalid choice. Please select a valid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid input. Please enter a number between 1 and "
                        + currentQuestion.getOptions().length + " or Q to quit");
            }
        }

        System.out.println("--------------------------------------------------");
        if (mistakes >= maxMistakesAllowed) {
            System.out.println("Game Over: You made 5 mistakes");
        } else {
            System.out.println("Thank you for playing the quiz game!");
        }
        System.out.println("You scored " + totalScore + " out of " + questionCount);

        System.out.println("--------------------------------------------------");
        while (true) {
            System.out.println("To go back to the menu, press '#'");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("#")) {
                menu();
                break;
            } else {
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid input, You must enter '#' to go back to the menu");
                System.out.println("--------------------------------------------------");
            }
        }
    }

    @Override
    public void manageGame() {
        while (true) {
            manageQuiz();
        }
    }

    private void manageQuiz() {
        System.out.println("********Manage Quiz********");
        System.out.println("Press '1' to Add a Question");
        System.out.println("Press '2' to View All Questions");
        System.out.println("Press '3' to View a Question by ID");
        System.out.println("Press '4' to Delete a Question");
        System.out.println("Press '5' to Go Back to Menu");
        System.out.println("***************************");

        System.out.print("Enter your choice: ");
        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Only numbers are supported. Please select according to the manage quiz menu.");
            System.out.println("--------------------------------------------------");
            return;
        }

        switch (choice) {
            case 1:
                save();  
                break;
            case 2:
                getAll();
                break;
            case 3:
                getById(choice);
                break;
            case 4:
                delete(choice);
                break;
            case 5:
                menu();
                return;
            default:
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid choice. Please select according to the manage quiz menu.");
                System.out.println("--------------------------------------------------");
                break;
        }
    }

    @Override
    public boolean save() {  
        System.out.println("--------------------------------------------------");
        System.out.print("Enter the number of questions to add: ");

        try {
            int numQuestions = Integer.parseInt(scanner.nextLine());
            if (questions == null) {
                questions = new Question[numQuestions];
            } else {
                Question[] newQuestions = new Question[currentIndex + numQuestions];
                System.arraycopy(questions, 0, newQuestions, 0, currentIndex);
                questions = newQuestions;
            }

            for (int i = 0; i < numQuestions; i++) {
                System.out.println("--------------------------------------------------");
                System.out.print("Enter question: ");
                String questionText = scanner.nextLine();

                System.out.println("--------------------------------------------------");
                System.out.print("Enter number of options: ");
                int numOptions = Integer.parseInt(scanner.nextLine());
                Option[] options = new Option[numOptions];

                for (int j = 0; j < numOptions; j++) {
                    System.out.print("Option " + (j + 1) + ": ");
                    String optionText = scanner.nextLine();
                    System.out.print("Is this option correct? (y/n): ");
                    boolean isCorrect = scanner.nextLine().equalsIgnoreCase("y");
                    options[j] = new Option(optionText, isCorrect);
                }

                questions[currentIndex++] = new Question(questionText, options);
                System.out.println("--------------------------------------------------");
                System.out.println("Question added successfully!");

            }
            return true;
        } catch (Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Only numbers are supported. Please enter valid input.");
            System.out.println("--------------------------------------------------");
            return false;
        }
    }

    @Override
    public Question[] getAll() {
        if (questions == null || currentIndex == 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("No questions available. Please add questions first.");
            System.out.println("--------------------------------------------------");
            return null;
        }
    
        System.out.println("--------------------------------------------------");
        System.out.println("List of All Questions:");
        for (int i = 0; i < currentIndex; i++) {
            Question question = questions[i];
            System.out.println("--------------------------------------------------");
            System.out.println(question.getId() + "). Question: " + question.getQuestionText());
            for (int j = 0; j < question.getOptions().length; j++) {
                System.out.println((j + 1) + ". " + question.getOptions()[j].getName());
            }
        }
        System.out.println("--------------------------------------------------");
        return questions;
    }
    
    

    @Override
    public Question getById(Integer id) {
        System.out.println("Get by ID");
        return null;
    }
    

    @Override
    public boolean delete(Integer id) {
        System.out.println("Delete");
        return false;
    }
    

    @Override
    public void quitGame() {
        System.out.println("--------------------------------------------------");
        System.out.println("Thank you for playing the quiz game. Goodbye!");
        System.out.println("--------------------------------------------------");
    }
}

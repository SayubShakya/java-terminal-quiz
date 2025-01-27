import java.util.InputMismatchException;
import java.util.Scanner;

class QuizImpl implements Quizable {
    private static Question[] questions; // fixedsize array ko questions
    private static int currentQuestionCount = 0;
    private static Scanner scanner = null;

    @Override
    public void menu() {
        while (true) {
            gameManager();
        }
    }

    private void gameManager() {
        scanner = new Scanner(System.in);
        System.out.println("********Menu********");
        System.out.println("Press 1 to Start Quiz");
        System.out.println("Press 2 to Manage Quiz");
        System.out.println("Press 3 to Quit Quiz");
        System.out.println("********************");

        System.out.print("Enter your choice: ");
        int choice = 0;

        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Only Integer value are supported. Please select according to menu");
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
                System.out.println("Invalid choice. Please select according to menu");
                System.out.println("--------------------------------------------------");
                break;
        }
    }

    @Override
    public void startGame() {
        if (currentQuestionCount == 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("Please add questions before starting the quiz in Manage Quiz");
            System.out.println("--------------------------------------------------");
            menu();
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Welcome to the Quiz Game!");

        int totalScore = 0;
        int questionCount = 0;

        int mistakes = 0;
        int maxMistakesAllowed = 5;

        while (questionCount < currentQuestionCount && mistakes < maxMistakesAllowed) {
            Question currentQuestion = questions[questionCount];

            System.out.println("--------------------------------------------------");
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
            System.out.println("********Manage Quiz********");
            System.out.println("Press 1 to Add a Question");
            System.out.println("Press 2 to View All Questions");
            System.out.println("Press 3 to Delete a Question");
            System.out.println("Press 4 to Go Back to Menu");
            System.out.println("***************************");

            System.out.print("Enter your choice: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid choice. Please select according to Manage Quiz Menu.");
                System.out.println("--------------------------------------------------");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    if (questions == null) {
                        System.out.println("--------------------------------------------------");
                        System.out.print("Enter the number of questions you want to add: ");
                        int size;

                        while (true) {
                            try {
                                size = Integer.parseInt(scanner.nextLine());
                                if (size > 0) {
                                    break;
                                } else {
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("Please enter a positive number.");
                                    System.out.println("--------------------------------------------------");

                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                System.out.println("--------------------------------------------------");
                            }
                        }


                        
                        int sizeOfQuestion = 0;
                        if(questions != null && questions.length > 0) {
                                sizeOfQuestion = questions.length;
                        }
                        Question[] tempQuestion = new Question[sizeOfQuestion+size]; // user sanga array size magxa

                        if(sizeOfQuestion > 0) {
                            for(int i=0; i<sizeOfQuestion; i++) {
                                tempQuestion[i] = questions[i];
                            }
                        }

                        questions = tempQuestion;
                        currentQuestionCount=sizeOfQuestion;

                    }
                    addQuestion();
                }
                case 2 -> viewQuestions();
                case 3 -> deleteQuestion();
                case 4 -> {
                    menu();
                    return;
                }
                default -> System.out.println("Invalid choice. Please select according to Manage Quiz Menu.");

            }
        }
    }

    private void addQuestion() {
        if (currentQuestionCount >= questions.length) {
            System.out.println("--------------------------------------------------");
            System.out.println("You cannot add more questions. Maximum limit reached.");
            System.out.println("--------------------------------------------------");
            return;
        }
9
        System.out.println("Enter the question:");
        String questionText = scanner.nextLine();

        System.out.print("Enter the number of options for this question: ");
        int optionCount = Integer.parseInt(scanner.nextLine());

        Option[] options = new Option[optionCount];

        for (int i = 0; i < optionCount; i++) {
            System.out.print("Option " + (i + 1) + ": ");
            String optionText = scanner.nextLine();

            System.out.print("Is this option correct? (yes/no): ");
            boolean isCorrect = scanner.nextLine().equalsIgnoreCase("yes");

            options[i] = new Option(optionText, isCorrect);
        }

        questions[currentQuestionCount++] = new Question(questionText, options);
        System.out.println("--------------------------------------------------");
        System.out.println("Question added successfully!");
        System.out.println("--------------------------------------------------");
    }

    private void viewQuestions() {
        if (currentQuestionCount == 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("No questions available.");
            System.out.println("--------------------------------------------------");
            return;
        }

        for (int i = 0; i < currentQuestionCount; i++) {
            Question question = questions[i];
            System.out.println((i + 1) + ". " + question.getQuestionText());

            for (int j = 0; j < question.getOptions().length; j++) {
                System.out.println("    " + (j + 1) + ". " + question.getOptions()[j].getName());
            }
        }
    }

    private void deleteQuestion() {
        viewQuestions();

        if (currentQuestionCount == 0) {
            return;
        }

        System.out.print("Enter the question number to delete: ");
        try {
            int questionNumber = Integer.parseInt(scanner.nextLine());
            if (questionNumber >= 1 && questionNumber <= currentQuestionCount) {
                for (int i = questionNumber - 1; i < currentQuestionCount - 1; i++) {
                    questions[i] = questions[i + 1];
                }
                questions[--currentQuestionCount] = null;
                System.out.println("--------------------------------------------------");
                System.out.println("Question deleted successfully!");
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid question number.");
                System.out.println("--------------------------------------------------");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.println("--------------------------------------------------");
        }
    }

    @Override
    public void quitGame() {
        System.out.println("--------------------------------------------------");
        System.out.println("Thank you for playing the quiz game. Goodbye!");
        System.out.println("--------------------------------------------------");
        System.exit(0);
    }
}
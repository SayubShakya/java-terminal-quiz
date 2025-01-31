import java.util.Scanner;

class QuizImpl implements Quizable {
    private QuestionRepository questionRepository;
    private Scanner scanner = new Scanner(System.in);

    public QuizImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

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
        int correct = 0;
        int played = 0;
        Question[] questions = questionRepository.getAll();

        if (questions == null || questions.length == 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("Please add questions before starting the quiz in Manage Quiz.");
            System.out.println("--------------------------------------------------");
            menu();
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Welcome to the Quiz Game!");
        System.out.println("--------------------------------------------------");

        for (Question question : questions) {

            System.out.println("Question: " + question.getId() + ". "+ question.getQuestionText());

            for (Option option : question.getOptions().getAll()) {
                System.out.println(option.getId() + ". " + option.getName());
            }

            for (int i = 0; i < 3; i++) {
                try {
                    System.out.print("Your answer: ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    System.out.println("--------------------------------------------------");
                    System.out.println("");
                    Option chosenOption = null;

                    for (Option option : question.getOptions().getAll()) {
                        if (option.getId() == choice) {
                            chosenOption = option;
                        }
                    }

                    if (chosenOption == null) {
                        throw new RuntimeException();
                    }

                    if (chosenOption.isCorrect()) {
                        correct++;
                    }

                    break;

                } catch (Exception ex) {
                    continue;
                }
            }
            played++;
        }

        System.out.println("Thankyou for playing quiz game!");
        System.out.println("You scored: "+correct + " out of " + played);
        System.out.println("--------------------------------------------------");
        System.out.println("");


    }

    @Override
    public void manageGame() {
        while (true) {
            manageQuiz();
        }
    }

    private void manageQuiz() {
        System.out.println("--------------------------------------------------");
        System.out.println("********Manage Quiz********");
        System.out.println("Press '1' to Add a Questions");
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
                addQuestions();
                break;
            case 2:
                Question[] questions = questionRepository.getAll();
                for (Question question : questions) {
                    System.out.println(question);
                }
                break;
            case 3:
            System.out.println("--------------------------------------------------");
                System.out.print("Enter question ID: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    Question[] question1 = questionRepository.getById(id);
                    for (Question question : question1) {
                        System.out.println(question);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("There is no valid question ID.");
                    System.out.println("--------------------------------------------------");

                }
                break;
            case 4:
            System.out.println("--------------------------------------------------");
                System.out.print("Enter question ID to delete: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    questionRepository.deleteById(id);
                    System.out.println("--------------------------------------------------");
                    System.out.println("Question deleted successfully.");
                    System.out.println("--------------------------------------------------");
                } catch (Exception e) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Invalid input. Please enter a valid question ID.");
                    System.out.println("--------------------------------------------------");
                }
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
    public void quitGame() {
        System.out.println("--------------------------------------------------");
        System.out.println("Thank you for playing the quiz game. Goodbye!");
        System.out.println("--------------------------------------------------");
        scanner.close();
        System.exit(0);
    }

    public void addQuestions() {
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.print("Enter your question or 'Q' to go back: ");

            Question question = new Question();

            try {
                String text = scanner.nextLine();

                if (hasQ(text)) {
                    break;
                }

                isValidText(text);
                question.setQuestionText(text);
            } catch (Exception e) {
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid input. Please enter a valid question.");
                System.out.println("--------------------------------------------------");
                continue;
            }

            DynamicOptionArray dynamicOptionArray = new DynamicOptionArray();
            int optionId = 1;
            while (true) {

                if (handleOption(dynamicOptionArray, optionId)) {
                    break;
                } else {
                    optionId++;
                    continue;
                }
            }
            question.setOptions(dynamicOptionArray);
            questionRepository.save(question);
        }
    }

    public boolean handleOption(DynamicOptionArray dynamicOptionArray, int optionId) {
        Option option = new Option();
        option.setId(optionId);
        try {
            System.out.print("Enter your option or 'Q' to go back: ");
            String text = scanner.nextLine();

            if (hasQ(text))
                return true;

            isValidText(text);
            option.setName(text);

        } catch (Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Invalid input. Please enter a valid option.");
            System.out.println("--------------------------------------------------");
            return false;
        }

        try {
            System.out.print("Correct option: ");
            String text = scanner.nextLine();
            if (hasQ(text))
                return true;

            isValidText(text);

            if (text.equalsIgnoreCase("Y")) {
                option.setCorrect(true);
            } else if (text.equalsIgnoreCase("N")) {
                option.setCorrect(false);
            } else {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            System.out.println("--------------------------------------------------");
            System.out.println("Invalid input. Please enter a valid value (y/n).");
            System.out.println("--------------------------------------------------");
            return false;
        }

        dynamicOptionArray.add(option);
        return false;
    }

    public boolean hasQ(String text) {
        return "Q".equalsIgnoreCase(text);
    }

    public void isValidText(String text) {
        if (text == null || text.length() == 0) {
            throw new RuntimeException();
        }
    }

}

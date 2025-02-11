package com.sayub.game.impl;

import com.sayub.game.Manageable;
import com.sayub.game.Menuable;
import com.sayub.game.Quitable;
import com.sayub.game.Scoreable;
import com.sayub.game.Startable;
import com.sayub.model.Option;
import com.sayub.model.Question;
import com.sayub.model.Score;
import com.sayub.repository.QuestionRepository;
import com.sayub.repository.ScoreRepository;
import com.sayub.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameableQuizImpl implements Menuable, Startable, Manageable, Quitable, Scoreable {
    private final QuestionRepository questionRepository;
    private final ScoreRepository scoreRepository;
    private final Scanner scanner = new Scanner(System.in);

    public GameableQuizImpl(QuestionRepository questionRepository, ScoreRepository scoreRepository) {
        this.questionRepository = questionRepository;
        this.scoreRepository = scoreRepository;
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
        System.out.println("Press '3' to High Score");
        System.out.println("Press '4' to Quit Quiz");
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
                start();
                break;
            case 2:
                manage();
                break;

            case 3:
                highScore();
                break;
            case 4:
                quit();
                break;

            default:
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid choice. Please select according to the menu.");
                System.out.println("--------------------------------------------------");
                break;
        }
    }

    @Override
    public void start() {
        int correct = 0;
        int played = 0;
        List<Question> questions = questionRepository.getAll();
        if (questions == null || questions.isEmpty()) {
            System.out.println("--------------------------------------------------");
            System.out.println("Please add questions before starting the quiz in Manage Quiz.");
            System.out.println("--------------------------------------------------");
            menu();
            return;
        }


        Date start = new Date();

        System.out.println("--------------------------------------------------");
        System.out.println("Welcome to the Quiz Game!");
        System.out.println("--------------------------------------------------");
        System.out.println("Please enter your username: ");
        String name = scanner.nextLine();

        Score score = new Score();
        score.setUsername(name);
        for (Question question : questions) {
            System.out.println("Question: " + question.getId() + ". " + question.getTitle());


            if (question.getOptions() == null || question.getOptions().isEmpty()) {
                System.out.println("No options available.");
                continue;
            }

            for (Option option : question.getOptions()) {
                System.out.println(option.getId() + ". " + option.getName());
            }

            for (int i = 0; i < 3; i++) {
                try {
                    System.out.print("Your answer: ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    System.out.println("--------------------------------------------------");
                    System.out.println();
                    Option chosenOption = null;

                    for (Option option : question.getOptions()) {
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

        System.out.println("Thank you for playing the quiz game!");
        System.out.println("You scored: " + correct + " out of " + played);
        System.out.println("--------------------------------------------------");
        score.setScore(correct);

        Date end = new Date();
        long timeTaken = (end.getTime() - start.getTime()) / 1000;
        score.setTimeInSeconds((int) timeTaken);

        System.out.println("You took: " + timeTaken + " seconds");

        scoreRepository.save(score);
    }

    @Override
    public void manage() {
        while (true) {
            manageQuiz();
        }
    }

    private void manageQuiz() {
        System.out.println("--------------------------------------------------");
        System.out.println("********Manage Quiz********");
        System.out.println("Press '1' to Add a Questions");
        System.out.println("Press '2' to View All Questions");
        System.out.println("Press '3' to View Question by ID");
        System.out.println("Press '4' to Delete Question");
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
                List<Question> questions = questionRepository.getAll();
                if (questions != null) {
                    for (Question question : questions) {
                        System.out.println(question);
                    }
                }

                break;
            case 3:
                System.out.println("--------------------------------------------------");
                System.out.print("Enter question ID: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());

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
    public void quit() {
        System.out.println("--------------------------------------------------");
        System.out.println("Thank you for playing quiz. Goodbye!");
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
                question.setTitle(text);
            } catch (Exception e) {
                System.out.println("--------------------------------------------------");
                System.out.println("Invalid input. Please enter a valid question.");
                System.out.println("--------------------------------------------------");
                continue;
            }

            List<Option> dynamicOptionArray = new ArrayList<>();
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

    public boolean handleOption(List<Option> dynamicOptionArray, int optionId) {
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

    @Override
    public void highScore() {
        System.out.println("--------------------------------------------------");
        System.out.println("Username         Score          TimeInSeconds");
        for (Score score : scoreRepository.getAll()) {
            System.out.println("--------------------------------------------------");
            System.out.printf("%s              %d                %d\n", score.getUsername(), score.getScore(), score.getTimeInSeconds());
            System.out.println("--------------------------------------------------");
        }
    }
}

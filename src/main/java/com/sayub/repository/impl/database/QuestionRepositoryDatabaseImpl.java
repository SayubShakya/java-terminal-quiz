package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.model.Option;
import com.sayub.model.Question;
import com.sayub.model.QuestionOption;
import com.sayub.repository.OptionRepository;
import com.sayub.repository.QuestionOptionRepository;
import com.sayub.repository.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryDatabaseImpl implements QuestionRepository {

    private final OptionRepository optionRepository;
    private final QuestionOptionRepository questionOptionRepository;

    public QuestionRepositoryDatabaseImpl(OptionRepository optionRepository, QuestionOptionRepository questionOptionRepository) {
        this.optionRepository = optionRepository;
        this.questionOptionRepository = questionOptionRepository;
    }

    @Override
    public boolean save(Question question) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Question.SAVE, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, question.getTitle());

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int insertId = generatedKeys.getInt(1);
                    question.setId(insertId);
                    System.out.println("Record inserted successfully with ID: " + insertId);
                } else {
                    System.out.println("Failed to retrieve insert ID.");
                }
            } else {
                System.out.println("No records inserted.");
            }

            List<Option> options = question.getOptions();

            int questionId = question.getId();

            for (Option option : options) {
                optionRepository.save(option);
                questionOptionRepository.save(new QuestionOption(questionId, option.getId()));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();

        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Question.GETALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setTitle(resultSet.getString("title"));



                List<Option> options = getOptionsForQuestion(question.getId(), conn);
                question.setOptions(options); //******************

                questions.add(question);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return questions;
    }

    private List<Option> getOptionsForQuestion(int questionId, Connection conn) {
        List<Option> options = new ArrayList<>();

        try {
            PreparedStatement optionStatement = conn.prepareStatement(QueryConstant.OptionsForQuestion.GETALLOPTIONS);
            optionStatement.setInt(1, questionId);
            ResultSet optionResultSet = optionStatement.executeQuery();

            while (optionResultSet.next()) {
                Option option = new Option();
                option.setId(optionResultSet.getInt("id"));
                option.setName(optionResultSet.getString("name"));
                option.setCorrect(optionResultSet.getInt("is_correct") == 1);
                options.add(option);
            }
        } catch (Exception e) {
            System.out.println("Error fetching options for question " + questionId + ": " + e.getMessage());
        }
        return options;
    }

    @Override
    public Question getById(int id) {
        Question question = null;

        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Question.GETBYID);

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    question = new Question();
                    question.setId(resultSet.getInt("id"));
                    question.setTitle(resultSet.getString("title"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return question;
    }

    @Override
    public void deleteById(int id) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Question.DELETEBYID);
            preparedStatement.setInt(1, id);

            int rowAffected = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
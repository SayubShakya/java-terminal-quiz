package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.db.OptionRowMapper;
import com.sayub.db.QuestionRowMapper;
import com.sayub.model.Option;
import com.sayub.model.Question;
import com.sayub.repository.OptionRepository;
import com.sayub.repository.QuestionOptionRepository;
import com.sayub.repository.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        int questionId = DatabaseConnector.update(QueryConstant.Question.SAVE, question.getId());

        if (questionId != 0) {

            question.setId(questionId);

            optionRepository.saveAll(question.getOptions());

            questionOptionRepository.save(question);

            return true;
        }

        return false;
    }

    @Override
    public List<Question> getAll() {

        List<Question> questions = DatabaseConnector.queryAll(QueryConstant.Question.GET_ALL, new QuestionRowMapper());

        for (Question question : questions) {

            List<Option> options = DatabaseConnector
                    .queryAll(QueryConstant.Option.GET_MULTIPLE_BY_QUESTION_ID, new OptionRowMapper(), question.getId());

            question.setOptions(options);
        }

        return questions;
    }


    @Override
    public Question getById(int id) {
        Question question = null;

        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Question.GET_BY_ID);

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
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Question.DELETE_BY_ID);
            preparedStatement.setInt(1, id);

            int rowAffected = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
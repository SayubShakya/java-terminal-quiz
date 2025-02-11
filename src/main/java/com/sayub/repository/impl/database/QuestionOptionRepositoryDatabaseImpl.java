package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.model.Option;
import com.sayub.model.Question;
import com.sayub.model.QuestionOption;
import com.sayub.repository.QuestionOptionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionOptionRepositoryDatabaseImpl implements QuestionOptionRepository {
    @Override
    public List<QuestionOption> save(Question question) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.QuestionOption.SAVE, PreparedStatement.RETURN_GENERATED_KEYS);


            for (Option option : question.getOptions()) {
                preparedStatement.setInt(1, question.getId());
                preparedStatement.setInt(2, option.getId());
                preparedStatement.addBatch();
            }


            int[] rowsAffected = preparedStatement.executeBatch();

            if(rowsAffected.length != question.getOptions().size()){
                System.out.println("QuestionOption persist size is less than the data provided");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            List<QuestionOption> questionOptions = new ArrayList<>();

            for (Option option : question.getOptions()) {

                if (generatedKeys.next()) {

                    QuestionOption questionOption = new QuestionOption();

                    int questionOptionId = generatedKeys.getInt(1);

                    questionOption.setId(questionOptionId);

                    questionOption.setQuestion(question);

                    questionOption.setOption(option);

                    questionOptions.add(questionOption);

                } else {
                    System.out.println("Failed to retrieve insert ID.");
                }

            }
            return questionOptions;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
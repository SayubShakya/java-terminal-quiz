package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.model.QuestionOption;
import com.sayub.repository.QuestionOptionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuestionOptionRepositoryDatabaseImpl implements QuestionOptionRepository {
    @Override
    public boolean save(QuestionOption questionOption) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.QuestionOption.SAVE, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, questionOption.getQuestionId());
            preparedStatement.setInt(2, questionOption.getOptionId());


            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int insertId = generatedKeys.getInt(1);
                    questionOption.setId(insertId);
                    System.out.println("Record inserted successfully with ID: " + insertId);
                    return true;
                } else {
                    System.out.println("Failed to retrieve insert ID.");
                }
            } else {
                System.out.println("No records inserted.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }
}

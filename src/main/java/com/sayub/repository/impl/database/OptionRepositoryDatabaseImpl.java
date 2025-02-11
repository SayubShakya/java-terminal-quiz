package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.model.Option;
import com.sayub.repository.OptionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class OptionRepositoryDatabaseImpl implements OptionRepository {

    @Override
    public boolean save(Option option) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Option.SAVE, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, option.getName());
            preparedStatement.setInt(2, option.isCorrect() ? 1 : 0);

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int insertId = generatedKeys.getInt(1);
                    option.setId(insertId);
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

    @Override
    public boolean saveAll(List<Option> options) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnector.getConnection();
            preparedStatement = DatabaseConnector.getPreparedStatement(connection, QueryConstant.Option.SAVE);

            for (Option option : options) {
                preparedStatement.setString(1, option.getName());
                preparedStatement.setInt(2, option.isCorrect() ? 1 : 0);
                preparedStatement.addBatch();
            }

            int[] rowsAffected = preparedStatement.executeBatch();

            if (rowsAffected.length != options.size()) {
                System.out.println("Option persist size is less than the data provided");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            for (Option option : options) {
                if (generatedKeys.next()) {
                    int optionId = generatedKeys.getInt(1);
                    option.setId(optionId);
                } else {
                    System.out.println("Failed to retrieve insert ID.");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("######## Error saving options ########");
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnector.close(connection, preparedStatement);
        }
        return false;
    }
}
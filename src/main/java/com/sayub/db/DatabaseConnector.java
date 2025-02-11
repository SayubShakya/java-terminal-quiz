package com.sayub.db;

import java.sql.*;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/quiz_application";
    private static final String USER = "root";
    private static final String PASSWORD = "9828807288";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public static void close(Connection connection, PreparedStatement preparedStatement) {
        try {
            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (Exception e) {
            System.out.println("######## Error closing database connection ########");
            System.out.println(e.getMessage());
        }
    }

    public static int update(String sql, Object... params) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = getConnection();
            preparedStatement = getPreparedStatement(connection, sql);

            int paramCounter = 1;
            for (Object param : params) {

                preparedStatement.setObject(paramCounter++, param);

            }

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected > 0) {

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }

            }

        } catch (Exception e) {

            System.out.printf("######## Error updating query: %s", sql);
            System.out.println(e.getMessage());

        } finally {

            close(connection, preparedStatement);

        }

        return 0;
    }

    public static int[] updateBulk(String sql, Object[][] bulkParam) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = getConnection();
            preparedStatement = getPreparedStatement(connection, sql);

            for (Object[] params : bulkParam) {

                int paramCounter = 1;

                for (Object param : params) {
                    preparedStatement.setObject(paramCounter++, param);
                }

                preparedStatement.addBatch();
            }

            int[] rowsAffected = preparedStatement.executeBatch();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (rowsAffected.length != bulkParam.length) {

                System.out.println("Update persist size is less than the data provided");

            } else {

                int[] idArray = new int[bulkParam.length];

                for (int i = 0; i < idArray.length; i++) {

                    if (generatedKeys.next()) {

                        idArray[i] = generatedKeys.getInt(1);

                    }

                }

                return idArray;
            }

        } catch (Exception e) {

            System.out.printf("######## Error updating query: %s", sql);
            System.out.println(e.getMessage());

        } finally {

            close(connection, preparedStatement);

        }

        return new int[0];
    }
}

package com.sayub.db;

import com.sayub.constant.QueryConstant;

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
            if(connection != null) {
                connection.close();
            }

            if(preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (Exception e) {
            System.out.println("######## Error closing database connection ########");
            System.out.println(e.getMessage());
        }
    }
}

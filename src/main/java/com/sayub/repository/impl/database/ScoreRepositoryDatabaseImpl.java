package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.model.Score;
import com.sayub.repository.ScoreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoreRepositoryDatabaseImpl implements ScoreRepository {
    @Override
    public List<Score> getAll() {
        List<Score> scores = new ArrayList<>();

        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Score.GETALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Score score = new Score();
                score.setId(resultSet.getInt("id"));
                score.setUsername(resultSet.getString("username"));
                score.setScore(resultSet.getInt("score"));
                score.setTimeInSeconds(resultSet.getLong("time_in_seconds"));
                scores.add(score);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return scores;
    }

    @Override
    public boolean save(Score score) {
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(QueryConstant.Score.SAVE);

            preparedStatement.setString(2, score.getUsername());
            preparedStatement.setInt(3, score.getScore());
            preparedStatement.setLong(4, score.getTimeInSeconds());

            int rowAffected = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}

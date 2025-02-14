package com.sayub.db.query.impl;

import com.sayub.db.query.RowMapper;
import com.sayub.model.Score;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreRowMapper implements RowMapper<Score> {
    @Override
    public Score map(ResultSet resultSet) throws SQLException {
        Score score = new Score();
        score.setId(resultSet.getInt("id"));
        score.setUsername(resultSet.getString("username"));
        score.setScore(resultSet.getInt("score"));
        score.setTimeInSeconds(resultSet.getInt("time_in_second"));
        return score;
    }
}
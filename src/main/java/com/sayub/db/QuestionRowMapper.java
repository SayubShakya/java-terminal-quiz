package com.sayub.db;

import com.sayub.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question map(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setId(resultSet.getInt("id"));
        question.setTitle(resultSet.getString("title"));
        return question;
    }
}

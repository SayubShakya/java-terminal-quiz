package com.sayub.db;

import com.sayub.model.Option;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OptionRowMapper implements RowMapper<Option> {

    @Override
    public Option map(ResultSet resultSet) throws SQLException {
        Option option = new Option();
        option.setId(resultSet.getInt("id"));
        option.setName(resultSet.getString("name"));
        option.setCorrect(resultSet.getInt("is_correct") == 1);
        return option;
    }
}

package com.sayub.db.update.impl;

import com.sayub.db.update.QueryParamMapper;
import com.sayub.model.Option;
import com.sayub.model.Question;

import java.util.List;

public class QuestionOptionQueryParamMapper implements QueryParamMapper<Question, Object[][]> {

    @Override
    public Object[][] map(Question question) {

        List<Option> options = question.getOptions();

        Object[][] bulkParams = new Object[options.size()][];

        for (int i = 0; i < bulkParams.length; i++) {

            bulkParams[i] = new Object[]{question.getId(), options.get(i).getId()};

        }

        return bulkParams;
    }
}
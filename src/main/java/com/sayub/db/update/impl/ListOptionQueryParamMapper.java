package com.sayub.db.update.impl;

import com.sayub.db.update.QueryParamMapper;
import com.sayub.model.Option;

import java.util.List;

public class ListOptionQueryParamMapper implements QueryParamMapper<List<Option>, Object[][]> {

    @Override
    public Object[][] map(List<Option> options) {

        Object[][] bulkParams = new Object[options.size()][];

        for (int i = 0; i < bulkParams.length; i++) {

            Option option = options.get(i);
            bulkParams[i] = new Object[]{option.getName(), option.isCorrect() ? 1 : 0};

        }

        return bulkParams;
    }
}
package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.model.Option;
import com.sayub.model.Question;
import com.sayub.repository.QuestionOptionRepository;

import java.util.List;

public class QuestionOptionRepositoryDatabaseImpl implements QuestionOptionRepository {

    @Override
    public boolean save(Question question) {
        List<Option> options = question.getOptions();

        Object[][] bulkParams = new Object[options.size()][];

        for (int i = 0; i < bulkParams.length; i++) {
            bulkParams[i] = new Object[]{question.getId(), options.get(i).getId()};
        }

        int[] savedIdArray = DatabaseConnector.updateBulk(QueryConstant.QuestionOption.SAVE, bulkParams);

        return savedIdArray.length == options.size();

    }
}
package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.db.update.impl.QuestionOptionQueryParamMapper;
import com.sayub.model.Question;
import com.sayub.repository.QuestionOptionRepository;

public class QuestionOptionRepositoryDatabaseImpl implements QuestionOptionRepository {

    @Override
    public boolean save(Question question) {
        int[] savedIdArray = DatabaseConnector.updateBulk(QueryConstant.QuestionOption.SAVE, question, new QuestionOptionQueryParamMapper());
        return savedIdArray.length == question.getOptions().size();
    }
}
package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.model.Option;
import com.sayub.repository.OptionRepository;

import java.util.List;

public class OptionRepositoryDatabaseImpl implements OptionRepository {


    @Override
    public boolean saveAll(List<Option> options) {

        Object[][] bulkParams = new Object[options.size()][];

        for (int i = 0; i < bulkParams.length; i++) {
            Option option = options.get(i);
            bulkParams[i] = new Object[]{option.getName(), option.isCorrect() ? 1 : 0};
        }

        int[] savedIdArray = DatabaseConnector.updateBulk(QueryConstant.Option.SAVE, bulkParams);

        return savedIdArray.length == options.size();

    }
}
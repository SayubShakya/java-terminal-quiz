package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.db.update.impl.ListOptionQueryParamMapper;
import com.sayub.model.Option;
import com.sayub.repository.OptionRepository;

import java.util.List;

public class OptionRepositoryDatabaseImpl implements OptionRepository {

    @Override
    public boolean saveAll(List<Option> options) {

        int[] savedIdArray = DatabaseConnector
                .updateBulk(QueryConstant.Option.SAVE, options, new ListOptionQueryParamMapper());

        return savedIdArray.length == options.size();

    }

}
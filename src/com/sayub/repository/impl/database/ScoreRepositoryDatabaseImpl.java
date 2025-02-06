package com.sayub.repository.impl.database;

import com.sayub.model.Question;
import com.sayub.repository.QuestionRepository;

import java.util.List;

public class ScoreRepositoryDatabaseImpl implements QuestionRepository {
    @Override
    public boolean save(Question question) {

        return false;
    }

    @Override
    public List<Question> getAll() {

        return List.of();
    }

    @Override
    public Question getById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}

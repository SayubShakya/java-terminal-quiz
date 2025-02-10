package com.sayub.repository.impl.memory;

import com.sayub.model.Option;
import com.sayub.model.Question;
import com.sayub.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

public class QuizRepositoryMemoryImpl implements QuestionRepository {

    protected List<Question> questions = new ArrayList<>();

    @Override
    public boolean save(Question question) {
        questions.add(question);
        return true;
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }

    @Override
    public Question getById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {
    }

}

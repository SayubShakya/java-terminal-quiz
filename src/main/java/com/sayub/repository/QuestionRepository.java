package com.sayub.repository;

import com.sayub.model.Question;

import java.util.List;

public interface QuestionRepository {
    boolean save(Question question);

    List<Question> getAll();

    Question getById(int id);

    void deleteById(int id);

}


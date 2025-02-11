package com.sayub.repository;

import com.sayub.model.Question;
import com.sayub.model.QuestionOption;

import java.util.List;

public interface QuestionOptionRepository {
    List<QuestionOption> save(Question question);
}

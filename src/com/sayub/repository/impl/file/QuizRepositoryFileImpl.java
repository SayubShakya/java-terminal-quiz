package com.sayub.repository.impl.file;

import com.sayub.converter.JavaObjectFileConverter;
import com.sayub.model.Question;
import com.sayub.repository.impl.memory.QuizRepositoryMemoryImpl;

import java.util.List;

public class QuizRepositoryFileImpl extends QuizRepositoryMemoryImpl {

    public static final String FILE_NAME = "questions";

    private final JavaObjectFileConverter<List<Question>> converter;

    public QuizRepositoryFileImpl(JavaObjectFileConverter<List<Question>> converter) {
        this.converter = converter;
        fetchExistingQuestionsFromFile(converter);
    }

    private void fetchExistingQuestionsFromFile(JavaObjectFileConverter<List<Question>> converter) {
        List<Question> questions = converter.deserialize(FILE_NAME);
        if (questions != null) {
            this.questions = questions;
        }
    }

    @Override
    public boolean save(Question question) {
        boolean save = super.save(question);
        converter.serialize(questions, FILE_NAME);
        return save;
    }
}

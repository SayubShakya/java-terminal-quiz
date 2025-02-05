package repository.impl;

import model.Question;
import util.converter.JavaObjectConverterUtil;

import java.util.List;

public class QuizRepositoryFileImpl extends QuizRepositoryMemoryImpl {

    public static final String QUESTIONS = "questions";

    private JavaObjectConverterUtil<List<Question>> converter;

    public QuizRepositoryFileImpl(JavaObjectConverterUtil<List<Question>> converter) {
       this.converter = converter;
        List<Question> fileQuestions = converter.deserialize(QUESTIONS);
        if(fileQuestions != null) {
            questions = fileQuestions;
        }
        System.out.println("question size: "+questions.size());
    }

    @Override
    public boolean save(Question question) {
        boolean save = super.save(question);
        converter.serialize(questions, QUESTIONS);
        return save;
    }
}

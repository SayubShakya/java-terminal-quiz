package repository.impl;

import array.DynamicQuestionArray;
import model.Question;
import util.converter.JavaObjectConverterUtil;

public class QuizRepositoryFileImpl extends QuizRepositoryMemoryImpl {

    public static final String QUESTIONS = "questions";

    private JavaObjectConverterUtil<DynamicQuestionArray> converter;

    public QuizRepositoryFileImpl(JavaObjectConverterUtil<DynamicQuestionArray> converter) {
       this.converter = converter;
        DynamicQuestionArray fileQuestions = converter.deserialize(QUESTIONS);
        System.out.println(fileQuestions);
        if(fileQuestions != null) {
            questions = fileQuestions;
        }
    }

    @Override
    public boolean save(Question question) {
        boolean save = super.save(question);
        converter.serialize(questions, "questions");
        return save;
    }
}

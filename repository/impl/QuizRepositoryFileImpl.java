package repository.impl;

import array.ListArrayImpl;
import model.Question;
import util.converter.JavaObjectConverterUtil;

public class QuizRepositoryFileImpl extends QuizRepositoryMemoryImpl {

    public static final String QUESTIONS = "questions";

    private JavaObjectConverterUtil<ListArrayImpl> converter;

    public QuizRepositoryFileImpl(JavaObjectConverterUtil<ListArrayImpl> converter) {
       this.converter = converter;
        ListArrayImpl fileQuestions = converter.deserialize(QUESTIONS);
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

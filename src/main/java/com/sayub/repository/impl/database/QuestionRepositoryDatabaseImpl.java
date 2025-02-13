package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.db.query.impl.OptionRowMapper;
import com.sayub.db.query.impl.QuestionRowMapper;
import com.sayub.model.Option;
import com.sayub.model.Question;
import com.sayub.repository.OptionRepository;
import com.sayub.repository.QuestionOptionRepository;
import com.sayub.repository.QuestionRepository;

import java.util.List;

public class QuestionRepositoryDatabaseImpl implements QuestionRepository {

    private final OptionRepository optionRepository;
    private final QuestionOptionRepository questionOptionRepository;

    public QuestionRepositoryDatabaseImpl(OptionRepository optionRepository, QuestionOptionRepository questionOptionRepository) {
        this.optionRepository = optionRepository;
        this.questionOptionRepository = questionOptionRepository;
    }

//    @Override
//    public boolean save(Question question) {
//
//        int questionId = DatabaseConnector.update(QueryConstant.Question.SAVE, question.getId());
//
//        if (questionId != 0) {
//
//            question.setId(questionId);
//
//            optionRepository.saveAll(question.getOptions());
//
//            questionOptionRepository.save(question);
//
//            return true;
//        }
//
//        return false;
//    }

    @Override
    public boolean save(Question question) {
        int questionId = DatabaseConnector.update(QueryConstant.Question.SAVE, question.getTitle());
        if (questionId != 0) {
            question.setId(questionId);

            // Save options
            boolean optionsSaved = optionRepository.saveAll(question.getOptions());
            if (!optionsSaved) {
                return false;
            }

            // Save question-option mapping
            return questionOptionRepository.save(question);
        }
        return false;
    }

    @Override
    public List<Question> getAll() {

        List<Question> questions = DatabaseConnector.queryAll(QueryConstant.Question.GET_ALL, new QuestionRowMapper());

        for (Question question : questions) {

            List<Option> options = DatabaseConnector
                    .queryAll(QueryConstant.Option.GET_MULTIPLE_BY_QUESTION_ID, new OptionRowMapper(), question.getId());

            question.setOptions(options);
        }

        return questions;
    }


    @Override
    public Question getById(int id) {

        Question question;

        question = DatabaseConnector.queryOne(QueryConstant.Question.GET_BY_ID, new QuestionRowMapper(), id);

//        for (Question questions : getAll()) {
//            if (questions.getId() == id) {
//                return questions;
//            }
//
//        }


        return question;
    }


//    @Override
//    public Question deleteById(int id) {
//        DatabaseConnector.update(QueryConstant.Question.DELETE_BY_ID, id);
//        return null;
//    }


    @Override
    public Question deleteById(int id) {
        Question question = getById(id);
        if (question == null) {
            return null;
        }

        DatabaseConnector.update(QueryConstant.Question.DELETE_FROM_QUESTION_OPTIONS, id);
        DatabaseConnector.update(QueryConstant.Question.DELETE_OPTIONS_BY_QUESTION_ID, id);
        DatabaseConnector.update(QueryConstant.Question.DELETE_BY_ID, id);
        return question;
    }
}
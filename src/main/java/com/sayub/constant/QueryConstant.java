package com.sayub.constant;

public interface QueryConstant {
    interface Question {
        String SAVE = "INSERT INTO questions(title) VALUES (?)";
        String GET_ALL = "SELECT * FROM questions";
        String GET_BY_ID = "SELECT * FROM questions WHERE id = ?";
        String DELETE_FROM_QUESTION_OPTIONS = "DELETE FROM questions_options WHERE question_id = ?";
        String DELETE_OPTIONS_BY_QUESTION_ID = "DELETE FROM options WHERE id IN " +
                "(SELECT option_id FROM questions_options WHERE question_id = ?)";
        String DELETE_BY_ID = "DELETE FROM questions WHERE id = ?";

    }

    interface Option {
        String SAVE = "INSERT INTO options(name, is_correct) VALUES (?,?)";
        String GET_MULTIPLE_BY_QUESTION_ID = "SELECT o.id, o.name, o.is_correct " +
                "FROM options o " +
                "JOIN questions_options qo ON o.id = qo.option_id " +
                "WHERE qo.question_id = ?";
    }


    interface QuestionOption {
        String SAVE = "INSERT INTO questions_options(question_id, option_id) VALUES (?,?)";
    }

    interface Score {
        String SAVE = "INSERT INTO scores(username, score, time_in_second) VALUES (?,?,?)";
        String GET_ALL = "SELECT * FROM scores";
    }
}






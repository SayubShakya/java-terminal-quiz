package com.sayub.constant;

public interface QueryConstant {
    interface Question {
        String SAVE = "INSERT INTO questions(title) VALUES (?)";
        String GETALL = "SELECT * FROM questions";
        String GETBYID = "SELECT * FROM questions WHERE id = ?";
        String DELETEBYID = "DELETE FROM questions WHERE id = ?";
    }

    interface Option {
        String SAVE = "INSERT INTO options(name, isCorrect) VALUES (?,?)";
    }

    interface QuestionOption {
        String SAVE = "INSERT INTO question_options(question_id, option_id) VALUES (?,?)";
    }

    interface Score{
        String SAVE = "INSERT INTO scores() VALUES (?,?,?)";
        String GETALL = "SELECT * FROM scores";
    }
}

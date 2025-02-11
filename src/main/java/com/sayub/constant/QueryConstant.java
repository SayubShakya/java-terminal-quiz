package com.sayub.constant;

public interface QueryConstant {
    interface Question {
        String SAVE = "INSERT INTO questions(title) VALUES (?)";
        String GETALL = "SELECT * FROM questions";
        String GETBYID = "SELECT * FROM questions WHERE id = ?";
        String DELETEBYID = "DELETE FROM questions WHERE id = ?";
    }

    interface Option {
        String SAVE = "INSERT INTO options(name, is_correct) VALUES (?,?)";
    }

    interface OptionsForQuestion{
        String GETALLOPTIONS = "SELECT o.id, o.name, o.is_correct " +
                "FROM options o " +
                "JOIN questions_options qo ON o.id = qo.option_id " +
                "WHERE qo.question_id = ?";
    }


    interface QuestionOption {
        String SAVE = "INSERT INTO questions_options(question_id, option_id) VALUES (?,?)";
    }

    interface Score{
        String SAVE = "INSERT INTO scores(username, score, time_in_second) VALUES (?,?,?)";
        String GETALL = "SELECT * FROM scores";
    }
}

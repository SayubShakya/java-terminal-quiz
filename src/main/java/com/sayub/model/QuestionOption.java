package com.sayub.model;

public class QuestionOption {
    private int id;
    private int questionId;
    private int optionId;

    public QuestionOption(int questionId, int optionId) {
        this.questionId = questionId;
        this.optionId = optionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }
}

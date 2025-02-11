package com.sayub.model;

public class QuestionOption {
    private int id;
    private Question question;
    private Option option;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "QuestionOption{" +
                "id=" + id +
                ", question=" + question +
                ", option=" + option +
                '}';
    }
}

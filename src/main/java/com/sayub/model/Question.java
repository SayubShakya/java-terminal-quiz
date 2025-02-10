package com.sayub.model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private static int idTracker = 1;
    private int id;
    private String title;
    private List<Option> options;

    public void setId(int id) {
        this.id = id;
    }

    public Question() {
        this.id = idTracker++;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String text) {
        this.title = text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        return options.get(answerIndex).isCorrect();
    }

    @Override
    public String toString() {
        return "Question " + id + ":" + title + ", options =" + options;
    }

}
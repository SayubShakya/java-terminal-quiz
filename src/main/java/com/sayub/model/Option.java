package com.sayub.model;

import java.io.Serializable;

public class Option implements Serializable {

    private int id;
    private String name; // options haru
    private boolean isCorrect; // option correct wala -T/F?

    public Option() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        return name + " (Correct: " + isCorrect + ")";
    }
}
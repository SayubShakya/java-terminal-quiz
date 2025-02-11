package com.sayub.model;

import java.io.Serializable;

public class Score implements Serializable {
    private int id;
    private String username;
    private int score;
    private int timeInSeconds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    @Override
    public String toString() {
        return "Score{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", timeInSeconds=" + timeInSeconds +
                '}';
    }
}
package com.sayub.repository;

import com.sayub.model.Score;

import java.util.List;

public interface ScoreRepository {
    List<Score> getAll();

    boolean save(Score score);
}


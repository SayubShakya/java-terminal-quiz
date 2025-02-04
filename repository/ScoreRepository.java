package repository;

import model.Score;

import java.util.ArrayList;
import java.util.List;

public interface ScoreRepository {
    List<Score> getAll();

    boolean save(Score score);
}

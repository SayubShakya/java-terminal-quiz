package com.sayub.repository.impl.database;

import com.sayub.constant.QueryConstant;
import com.sayub.db.DatabaseConnector;
import com.sayub.db.query.impl.ScoreRowMapper;
import com.sayub.model.Score;
import com.sayub.repository.ScoreRepository;

import java.util.List;

public class ScoreRepositoryDatabaseImpl implements ScoreRepository {

    @Override
    public List<Score> getAll() {
        return DatabaseConnector.queryAll(QueryConstant.Score.GET_ALL, new ScoreRowMapper());
    }


    @Override
    public boolean save(Score score) {
        // If user exists


        // check score is greater and time is less than the new data if yes - save else dont save
        // If user doesnt exists
        // save

        Score oldScore = DatabaseConnector.queryOne(QueryConstant.Score.GET_BY_USERNAME, new ScoreRowMapper());
        if (score == null) {
            if (oldScore != null) {

            }

        }


        int scoreId = DatabaseConnector.update(QueryConstant.Score.SAVE, score.getUsername(), score.getScore(), score.getTimeInSeconds());
        if (scoreId != 0) {
            score.setId(scoreId);
            return true;
        }
        return false;
    }
}
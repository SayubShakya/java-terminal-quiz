package repository.impl;

import model.Score;
import util.converter.JavaObjectConverterUtil;

import java.util.List;

public class ScoreRepositoryFileImpl extends ScoreRepositoryMemoryImpl {

    public static final String HIGH_SCORE = "high_scores";

    private JavaObjectConverterUtil<List<Score>> converter;

    public ScoreRepositoryFileImpl(JavaObjectConverterUtil<List<Score>> converter) {
       this.converter = converter;
        List<Score> fileScores = converter.deserialize(HIGH_SCORE);
        System.out.println(fileScores);
        if(fileScores != null) {
            scores = fileScores;
        }
    }

    @Override
    public boolean save(Score score) {
        boolean save = super.save(score);
        converter.serialize(scores, HIGH_SCORE);
        return save;
    }
}

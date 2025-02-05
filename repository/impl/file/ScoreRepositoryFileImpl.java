package repository.impl.file;

import model.Score;
import converter.JavaObjectFileConverter;
import repository.impl.memory.ScoreRepositoryMemoryImpl;

import java.util.List;

public class ScoreRepositoryFileImpl extends ScoreRepositoryMemoryImpl {

    public static final String FILE_NAME = "high_scores";

    private final JavaObjectFileConverter<List<Score>> converter;

    public ScoreRepositoryFileImpl(JavaObjectFileConverter<List<Score>> converter) {
        this.converter = converter;
        getScoreFromFile(converter);
    }

    private void getScoreFromFile(JavaObjectFileConverter<List<Score>> converter) {
        List<Score> fileScores = converter.deserialize(FILE_NAME);
        if (fileScores != null) {
            scores = fileScores;
        }
    }

    @Override
    public boolean save(Score score) {
        boolean save = super.save(score);
        converter.serialize(scores, FILE_NAME);
        return save;
    }
}

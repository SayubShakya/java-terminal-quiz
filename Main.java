import com.fasterxml.jackson.core.type.TypeReference;
import game.Menuable;
import game.impl.GameableQuizImpl;
import model.Question;
import model.Score;
import repository.impl.QuizRepositoryFileImpl;
import repository.impl.ScoreRepositoryFileImpl;
import util.converter.impl.JavaObjectWriterUtilJacksonImplFileJackson;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        startGame(
                new GameableQuizImpl(
                        new QuizRepositoryFileImpl(
                                new JavaObjectWriterUtilJacksonImplFileJackson<>(new TypeReference<List<Question>>() {
                                })
                        ),
                        new ScoreRepositoryFileImpl(
                                new JavaObjectWriterUtilJacksonImplFileJackson<>(new TypeReference<List<Score>>() {
                                }
                                )
                        )
                ));
    }

    public static void startGame(Menuable menuable) {
        menuable.menu();
    }

}
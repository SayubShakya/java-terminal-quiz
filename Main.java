import game.Menuable;
import game.impl.GameableQuizImpl;
import model.Question;
import model.Score;
import repository.impl.QuizRepositoryFileImpl;
import repository.impl.ScoreRepositoryFileImpl;
import util.converter.impl.JavaObjectConverterUtilJacksonImpl;
import util.converter.impl.JavaObjectConverterUtilJavaImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        JavaObjectConverterUtilJacksonImpl<List<Question>> questionSerializer = new JavaObjectConverterUtilJacksonImpl<>();
        JavaObjectConverterUtilJacksonImpl<List<Score>> optionSerializer = new JavaObjectConverterUtilJacksonImpl<>();

        startGame(
                new GameableQuizImpl(
                        new QuizRepositoryFileImpl(
                                new JavaObjectConverterUtilJavaImpl<>()
                        ),
                        new ScoreRepositoryFileImpl(
                                new JavaObjectConverterUtilJavaImpl<>()
                        )
                )
        );

    }

    public static void startGame(Menuable menuable) {
        menuable.menu();
    }

}
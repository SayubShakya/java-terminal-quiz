import com.fasterxml.jackson.core.type.TypeReference;
import converter.factory.JavaObjectFileConverterFactory;
import converter.factory.JavaObjectFileConverterType;
import game.Menuable;
import game.impl.GameableQuizImpl;
import repository.impl.file.QuizRepositoryFileImpl;
import repository.impl.file.ScoreRepositoryFileImpl;

public class Main {

    public static void main(String[] args) {
        startGame(
                new GameableQuizImpl(
                        new QuizRepositoryFileImpl(JavaObjectFileConverterFactory
                                .createConverter(JavaObjectFileConverterType.JACKSON, new TypeReference<>() {})),
                        new ScoreRepositoryFileImpl(JavaObjectFileConverterFactory
                                .createConverter(JavaObjectFileConverterType.JACKSON, new TypeReference<>() {}))
                        )
                );
    }

    public static void startGame(Menuable menuable) {
        menuable.menu();
    }

}
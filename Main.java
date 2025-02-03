import game.Gameable;
import game.impl.GameableQuizImpl;
import repository.impl.QuizRepositoryFileImpl;
import util.converter.impl.JavaObjectConverterUtilJavaImpl;
import util.converter.impl.JavaObjectConverterUtilSayubImpl;

public class Main {

    public static void main(String[] args) {
        startGame(new GameableQuizImpl(new QuizRepositoryFileImpl(new JavaObjectConverterUtilSayubImpl())));
    }

    public static void startGame(Gameable gameable) {
        gameable.menu();
    }
}
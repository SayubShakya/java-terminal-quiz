package com.sayub;

import com.sayub.game.Menuable;
import com.sayub.game.impl.GameableQuizImpl;
import com.sayub.repository.impl.database.OptionRepositoryDatabaseImpl;
import com.sayub.repository.impl.database.QuestionOptionRepositoryDatabaseImpl;
import com.sayub.repository.impl.database.QuestionRepositoryDatabaseImpl;
import com.sayub.repository.impl.database.ScoreRepositoryDatabaseImpl;

public class Main {

    public static void main(String[] args) {
//        startGame(
//                new GameableQuizImpl(
//                        new QuizRepositoryFileImpl(JavaObjectFileConverterFactory
//                                .createConverter(JavaObjectFileConverterType.JACKSON, new TypeReference<>() {})),
//                        new ScoreRepositoryFileImpl(JavaObjectFileConverterFactory
//                                .createConverter(JavaObjectFileConverterType.JACKSON, new TypeReference<>() {}))
//                        )
//                );
//    }


        startGame(
                new GameableQuizImpl(
                        new QuestionRepositoryDatabaseImpl(new OptionRepositoryDatabaseImpl(), new QuestionOptionRepositoryDatabaseImpl()
                        ),
                        new ScoreRepositoryDatabaseImpl()
                )
        );
    }

    public static void startGame(Menuable menuable) {
        menuable.menu();
    }
}
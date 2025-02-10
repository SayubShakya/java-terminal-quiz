package com.sayub;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sayub.converter.factory.JavaObjectFileConverterFactory;
import com.sayub.converter.factory.JavaObjectFileConverterType;
import com.sayub.game.Menuable;
import com.sayub.game.impl.GameableQuizImpl;
import com.sayub.repository.impl.file.QuizRepositoryFileImpl;
import com.sayub.repository.impl.file.ScoreRepositoryFileImpl;

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
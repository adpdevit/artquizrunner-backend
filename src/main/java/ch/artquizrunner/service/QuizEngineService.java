package ch.artquizrunner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.artquizrunner.model.QuizState;

@Service
public class QuizEngineService {

    @Autowired
    GameEngineService gameEngine;

    public QuizState getInitialQuizState() {
        final QuizState quizState = new QuizState();

        // TODO: Set first question of the quiz
        quizState.setGameState(gameEngine.getInitialGameState());

        return quizState;
    }

}

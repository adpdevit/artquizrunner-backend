package ch.artquizrunner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.artquizrunner.mapper.QuizMapper;
import ch.artquizrunner.model.QuizState;
import ch.artquizrunner.repository.QuestionRepository;

@Service
public class QuizEngineService {

    @Autowired
    private GameEngineService gameEngine;

    @Autowired
    private QuestionRepository questionRepository;

    public QuizState getInitialQuizState() {
        final QuizState quizState = new QuizState();

        // TODO: Set first question of the quiz
        quizState.setNextQuestion(
                QuizMapper.INSTANCE.questionEntityToDTO(questionRepository.getRandomQuestionEntity().get()));
        quizState.setGameState(gameEngine.getInitialGameState());

        return quizState;
    }

}

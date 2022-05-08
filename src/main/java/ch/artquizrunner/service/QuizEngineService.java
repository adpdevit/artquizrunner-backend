package ch.artquizrunner.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.artquizrunner.entity.AnswerEntity;
import ch.artquizrunner.entity.QuestionEntity;
import ch.artquizrunner.mapper.QuizMapper;
import ch.artquizrunner.model.GameState;
import ch.artquizrunner.model.PlayerAnswer;
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

    public QuizState verifyPlayerAnswerAndEvolveGameState(QuizState currentGameState, PlayerAnswer playerAnswer) {
        QuizState newQuizState = new QuizState();

        QuestionEntity answeredQuestion = questionRepository
                .getQuestionById(currentGameState.getNextQuestion().getId());
        Optional<AnswerEntity> emptyIfBadAnswer = answeredQuestion.getOptions().stream()
                .filter(answer -> Boolean.TRUE.equals(answer.getIsCorrect()))
                .filter(answer -> answer.getId().equals(playerAnswer.getChosenOption().getId())).findAny();

        GameState newGameState = null;
        if (emptyIfBadAnswer.isEmpty()) {
            newGameState = gameEngine.evolveGameStateNegative(currentGameState.getGameState());
        } else {
            newGameState = gameEngine.evolveGameStatePositive(currentGameState.getGameState());
        }

        newQuizState.setGameState(newGameState);
        newQuizState.setNextQuestion(
                QuizMapper.INSTANCE.questionEntityToDTO(questionRepository.getRandomQuestionEntity().get()));

        return newQuizState;
    }

}

package ch.artquizrunner.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.artquizrunner.entity.AnswerEntity;
import ch.artquizrunner.entity.QuestionEntity;
import ch.artquizrunner.entity.ScoreEntity;
import ch.artquizrunner.mapper.QuizMapper;
import ch.artquizrunner.model.GameState;
import ch.artquizrunner.model.PlayerAnswer;
import ch.artquizrunner.model.QuizState;
import ch.artquizrunner.repository.QuestionRepository;
import ch.artquizrunner.repository.ScoreRespository;

@Service
public class QuizEngineService {

    @Autowired
    private GameEngineService gameEngine;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ScoreRespository scoreRespository;

    public QuizState getInitialQuizState(String username) {
        final QuizState quizState = new QuizState();

        quizState.setUsername(username);
        quizState.setNextQuestion(
                QuizMapper.INSTANCE.questionEntityToDTO(questionRepository.getRandomQuestionEntity(null).get()));
        quizState.setGameState(gameEngine.getInitialGameState());

        return quizState;
    }

    public QuizState verifyPlayerAnswerAndEvolveGameState(QuizState currentGameState, PlayerAnswer playerAnswer) {
        QuizState newQuizState = new QuizState();
        newQuizState.setUsername(currentGameState.getUsername());
        if (currentGameState.getGameState().getEndState() > 0) {
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
            if (newGameState.getEndState() <= 0) {
                ScoreEntity score = new ScoreEntity();
                score.setScore(newGameState.getCurrentPoints());
                score.setUsername(newQuizState.getUsername());
                scoreRespository.addScore(score);
            } else {
                newQuizState.setNextQuestion(QuizMapper.INSTANCE.questionEntityToDTO(
                        questionRepository.getRandomQuestionEntity(answeredQuestion.getId()).get()));
            }
        }
        return newQuizState;
    }

}

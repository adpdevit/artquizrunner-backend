package ch.artquizrunner.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import ch.artquizrunner.api.QuizApi;
import ch.artquizrunner.model.PlayerAnswer;
import ch.artquizrunner.model.QuizState;
import ch.artquizrunner.service.QuizEngineService;
import ch.artquizrunner.util.JWTProcessor;
import ch.artquizrunner.util.QuizCookieGenerator;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class QuizController implements QuizApi {

    @Autowired
    private QuizEngineService quizEngineService;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private QuizCookieGenerator quizCookieGenerator;

    @Autowired
    private JWTProcessor jwtProcessor;

    @Override
    public ResponseEntity<QuizState> getInitialQuizState() {
        QuizState state = quizEngineService.getInitialQuizState();

        try {
            response.addCookie(
                    quizCookieGenerator.generateQuizCookie(jwtProcessor.generateAndSignQuizStateToken(state)));
        } catch (JsonProcessingException | IllegalArgumentException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Could not generate JWT", e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(quizEngineService.getInitialQuizState());
    }

    @Override
    public ResponseEntity<QuizState> handlePlayerChoice(@RequestBody PlayerAnswer playerAnswer) {
        QuizState newState = null;
        try {
            newState = quizEngineService.verifyPlayerAnswerAndEvolveGameState(
                    jwtProcessor.verifyAndGetQuizStateFromToken(quizCookieGenerator.getQuizCookieContent(request)),
                    playerAnswer);
            response.addCookie(
                    quizCookieGenerator.generateQuizCookie(jwtProcessor.generateAndSignQuizStateToken(newState)));
        } catch (JsonProcessingException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Could not generate JWT", e);
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(newState);
    }
}

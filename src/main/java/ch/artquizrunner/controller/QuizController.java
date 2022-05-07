package ch.artquizrunner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ch.artquizrunner.api.QuizApi;
import ch.artquizrunner.model.PlayerAnswer;
import ch.artquizrunner.model.QuizState;
import ch.artquizrunner.service.QuizEngineService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class QuizController implements QuizApi {

    @Autowired
    private QuizEngineService quizEngineService;

    @Override
    public ResponseEntity<QuizState> getInitialQuizState() {
        // TODO : Send an initial gamestate through cookie or http header
        return ResponseEntity.ok(quizEngineService.getInitialQuizState());
    }

    @Override
    public ResponseEntity<QuizState> handlePlayerChoice(@RequestBody PlayerAnswer playerAnswer) {
        // TODO : Send an initial gamestate through cookie or http header
        return ResponseEntity.ok(quizEngineService.getInitialQuizState());
    }
}

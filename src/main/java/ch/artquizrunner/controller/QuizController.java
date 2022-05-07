package ch.artquizrunner.controller;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.artquizrunner.api.QuizApi;
import ch.artquizrunner.model.ErrorDescription;
import ch.artquizrunner.model.GameState;
import ch.artquizrunner.model.PlayerAnswer;
import ch.artquizrunner.model.QuizState;
import ch.artquizrunner.service.GameEngineService;
import ch.artquizrunner.service.QuizEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

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

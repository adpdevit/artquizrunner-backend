package ch.artquizrunner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.artquizrunner.api.QuizApi;
import ch.artquizrunner.model.GameState;

@RestController
@RequestMapping("/api/user/quiz")
public class QuizController implements QuizApi {
	
	@GetMapping("/gamestate")
    public ResponseEntity<GameState> getInitialGameState() {
        // TODO : Send an initial gamestate through cookie or http header
		return null;
    }
}

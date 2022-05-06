package ch.artquizrunner.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/quiz")
public class QuizController {
	
	@GetMapping("/gamestate")
    public void getInitialGameState() {
        // TODO : Send an initial gamestate through cookie or http header
    }
}

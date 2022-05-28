package ch.artquizrunner.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.artquizrunner.api.ScoreApi;
import ch.artquizrunner.mapper.QuizMapper;
import ch.artquizrunner.model.Score;
import ch.artquizrunner.repository.ScoreRespository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@CrossOrigin(exposedHeaders = "*")
public class ScoreController implements ScoreApi {

    @Autowired
    private ScoreRespository scoreRepository;

    @Override
    public ResponseEntity<List<Score>> getBestScores(
            @Parameter(name = "limit", description = "the number of scores to get", schema = @Schema(description = "")) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        return ResponseEntity.ok(scoreRepository.getScoresOrderByDescScore(limit).stream()
                .map(QuizMapper.INSTANCE::scoreEntityToDTO).collect(Collectors.toList()));
    }
}

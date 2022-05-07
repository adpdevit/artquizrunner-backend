package ch.artquizrunner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.artquizrunner.api.AdminApi;
import ch.artquizrunner.mapper.QuizMapper;
import ch.artquizrunner.model.QuestionFull;
import ch.artquizrunner.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class AdminController implements AdminApi {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<QuestionFull> addQuestion(
            @Parameter(name = "Question", description = "", schema = @Schema(description = "")) @Valid @RequestBody(required = false) QuestionFull question) {
        return ResponseEntity.ok(QuizMapper.INSTANCE.questionEntityToFullDTO(
                questionRepository.addQuestion(QuizMapper.INSTANCE.questionFullDTOToEntity(question))));
    }
}

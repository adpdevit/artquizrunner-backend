package ch.artquizrunner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import ch.artquizrunner.api.AdminApi;
import ch.artquizrunner.mapper.Mapper;
import ch.artquizrunner.model.Question;
import ch.artquizrunner.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public class AdminController implements AdminApi {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<Question> addQuestion(
            @Parameter(name = "Question", description = "", schema = @Schema(description = "")) @Valid @RequestBody(required = false) Question question) {
        return ResponseEntity.ok(Mapper.INSTANCE
                .questionEntityToDTO(questionRepository.addQuestion(Mapper.INSTANCE.questionDTOToEntity(question))));
    }
}

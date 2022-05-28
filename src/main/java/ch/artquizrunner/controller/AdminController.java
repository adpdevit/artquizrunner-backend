package ch.artquizrunner.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.artquizrunner.api.AdminApi;
import ch.artquizrunner.mapper.QuizMapper;
import ch.artquizrunner.model.QuestionFull;
import ch.artquizrunner.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@CrossOrigin(exposedHeaders = "*")
public class AdminController implements AdminApi {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private QuestionRepository questionRepository;

    @Value("${admin.secret}")
    private String secret;

    @Override
    public ResponseEntity<QuestionFull> addQuestion(
            @Parameter(name = "Question", description = "", schema = @Schema(description = "")) @Valid @RequestBody(required = false) QuestionFull question) {
        if (secret.equals(request.getHeader("Authorization"))) {
            return ResponseEntity.ok(QuizMapper.INSTANCE.questionEntityToFullDTO(
                    questionRepository.addQuestion(QuizMapper.INSTANCE.questionFullDTOToEntity(question))));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<QuestionFull> deleteQuestionById(
            @Parameter(name = "id", description = "The comment that needs to be fetched.", required = true, schema = @Schema(description = "")) @PathVariable("id") Long id) {
        if (secret.equals(request.getHeader("Authorization"))) {
            return ResponseEntity
                    .ok(QuizMapper.INSTANCE.questionEntityToFullDTO(questionRepository.deleteQuestionById(id)));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<List<QuestionFull>> getAllQuestions() {
        if (secret.equals(request.getHeader("Authorization"))) {
            return ResponseEntity.ok(questionRepository.getQuestionList().stream()
                    .map(QuizMapper.INSTANCE::questionEntityToFullDTO).collect(Collectors.toList()));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

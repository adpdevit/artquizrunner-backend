package ch.artquizrunner.mapper;

import org.mapstruct.factory.Mappers;

import ch.artquizrunner.entity.AnswerEntity;
import ch.artquizrunner.entity.QuestionEntity;
import ch.artquizrunner.entity.ScoreEntity;
import ch.artquizrunner.model.AnswerOption;
import ch.artquizrunner.model.AnswerOptionFull;
import ch.artquizrunner.model.Question;
import ch.artquizrunner.model.QuestionFull;
import ch.artquizrunner.model.Score;

@org.mapstruct.Mapper
public interface QuizMapper {

    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    Question questionEntityToDTO(QuestionEntity entity);

    QuestionEntity questionDTOToEntity(Question dto);

    QuestionFull questionEntityToFullDTO(QuestionEntity entity);

    QuestionEntity questionFullDTOToEntity(QuestionFull dto);

    AnswerOption answerEntityToDTO(AnswerEntity entity);

    AnswerEntity answerDTOToEntity(AnswerOption entity);

    AnswerOptionFull answerEntityToFullDTO(AnswerEntity entity);

    AnswerEntity answerFullDTOToEntity(AnswerOptionFull entity);

    Score scoreEntityToDTO(ScoreEntity entity);
}

package ch.artquizrunner.mapper;

import org.mapstruct.factory.Mappers;

import ch.artquizrunner.entity.AnswerEntity;
import ch.artquizrunner.entity.QuestionEntity;
import ch.artquizrunner.model.AnswerOption;
import ch.artquizrunner.model.Question;

@org.mapstruct.Mapper
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    Question questionEntityToDTO(QuestionEntity entity);

    QuestionEntity questionDTOToEntity(Question dto);

    AnswerOption answerEntityToDTO(AnswerEntity entity);

    AnswerEntity answerDTOToEntity(AnswerOption entity);
}

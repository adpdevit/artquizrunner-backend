package ch.artquizrunner.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Generated;

import ch.artquizrunner.entity.AnswerEntity;
import ch.artquizrunner.entity.QuestionEntity;
import ch.artquizrunner.entity.ScoreEntity;
import ch.artquizrunner.model.AnswerOption;
import ch.artquizrunner.model.AnswerOptionFull;
import ch.artquizrunner.model.Question;
import ch.artquizrunner.model.QuestionFull;
import ch.artquizrunner.model.Score;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2022-05-29T12:13:41+0200", comments = "version: 1.4.1.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)")
public class QuizMapperImpl implements QuizMapper {

    @Override
    public Question questionEntityToDTO(QuestionEntity entity) {
        if (entity == null) {
            return null;
        }

        Question question = new Question();

        question.setId(entity.getId());
        question.setDescription(entity.getDescription());
        question.setOptions(answerEntitySetToAnswerOptionList(entity.getOptions()));

        return question;
    }

    @Override
    public QuestionEntity questionDTOToEntity(Question dto) {
        if (dto == null) {
            return null;
        }

        QuestionEntity questionEntity = new QuestionEntity();

        questionEntity.setId(dto.getId());
        questionEntity.setDescription(dto.getDescription());
        questionEntity.setOptions(answerOptionListToAnswerEntitySet(dto.getOptions()));

        return questionEntity;
    }

    @Override
    public QuestionFull questionEntityToFullDTO(QuestionEntity entity) {
        if (entity == null) {
            return null;
        }

        QuestionFull questionFull = new QuestionFull();

        questionFull.setId(entity.getId());
        questionFull.setDescription(entity.getDescription());
        questionFull.setOptions(answerEntitySetToAnswerOptionFullList(entity.getOptions()));

        return questionFull;
    }

    @Override
    public QuestionEntity questionFullDTOToEntity(QuestionFull dto) {
        if (dto == null) {
            return null;
        }

        QuestionEntity questionEntity = new QuestionEntity();

        questionEntity.setId(dto.getId());
        questionEntity.setDescription(dto.getDescription());
        questionEntity.setOptions(answerOptionFullListToAnswerEntitySet(dto.getOptions()));

        return questionEntity;
    }

    @Override
    public AnswerOption answerEntityToDTO(AnswerEntity entity) {
        if (entity == null) {
            return null;
        }

        AnswerOption answerOption = new AnswerOption();

        answerOption.setId(entity.getId());
        answerOption.setDescription(entity.getDescription());

        return answerOption;
    }

    @Override
    public AnswerEntity answerDTOToEntity(AnswerOption entity) {
        if (entity == null) {
            return null;
        }

        AnswerEntity answerEntity = new AnswerEntity();

        answerEntity.setId(entity.getId());
        answerEntity.setDescription(entity.getDescription());

        return answerEntity;
    }

    @Override
    public AnswerOptionFull answerEntityToFullDTO(AnswerEntity entity) {
        if (entity == null) {
            return null;
        }

        AnswerOptionFull answerOptionFull = new AnswerOptionFull();

        answerOptionFull.setId(entity.getId());
        answerOptionFull.setDescription(entity.getDescription());
        answerOptionFull.setIsCorrect(entity.getIsCorrect());

        return answerOptionFull;
    }

    @Override
    public AnswerEntity answerFullDTOToEntity(AnswerOptionFull entity) {
        if (entity == null) {
            return null;
        }

        AnswerEntity answerEntity = new AnswerEntity();

        answerEntity.setId(entity.getId());
        answerEntity.setDescription(entity.getDescription());
        answerEntity.setIsCorrect(entity.getIsCorrect());

        return answerEntity;
    }

    @Override
    public Score scoreEntityToDTO(ScoreEntity entity) {
        if (entity == null) {
            return null;
        }

        Score score = new Score();

        score.setId(entity.getId());
        score.setUsername(entity.getUsername());
        score.setScore(entity.getScore());

        return score;
    }

    protected List<AnswerOption> answerEntitySetToAnswerOptionList(Set<AnswerEntity> set) {
        if (set == null) {
            return null;
        }

        List<AnswerOption> list = new ArrayList<AnswerOption>(set.size());
        for (AnswerEntity answerEntity : set) {
            list.add(answerEntityToDTO(answerEntity));
        }

        return list;
    }

    protected Set<AnswerEntity> answerOptionListToAnswerEntitySet(List<AnswerOption> list) {
        if (list == null) {
            return null;
        }

        Set<AnswerEntity> set = new HashSet<AnswerEntity>(Math.max((int) (list.size() / .75f) + 1, 16));
        for (AnswerOption answerOption : list) {
            set.add(answerDTOToEntity(answerOption));
        }

        return set;
    }

    protected List<AnswerOptionFull> answerEntitySetToAnswerOptionFullList(Set<AnswerEntity> set) {
        if (set == null) {
            return null;
        }

        List<AnswerOptionFull> list = new ArrayList<AnswerOptionFull>(set.size());
        for (AnswerEntity answerEntity : set) {
            list.add(answerEntityToFullDTO(answerEntity));
        }

        return list;
    }

    protected Set<AnswerEntity> answerOptionFullListToAnswerEntitySet(List<AnswerOptionFull> list) {
        if (list == null) {
            return null;
        }

        Set<AnswerEntity> set = new HashSet<AnswerEntity>(Math.max((int) (list.size() / .75f) + 1, 16));
        for (AnswerOptionFull answerOptionFull : list) {
            set.add(answerFullDTOToEntity(answerOptionFull));
        }

        return set;
    }
}

package ch.artquizrunner.repository;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ch.artquizrunner.dao.SequenceDAO;
import ch.artquizrunner.entity.AnswerEntity;
import ch.artquizrunner.exception.SequenceException;

@Repository
public class AnswerRepository {

    private static final String ANSWER_SEQ_KEY = "answer_seq";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceDAO sequenceDAO;

    public AnswerEntity addAnswer(AnswerEntity answerEntity) {
        try {
            answerEntity.setId(sequenceDAO.getNextSequenceId(ANSWER_SEQ_KEY));
        } catch (SequenceException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Could not get next sequence", e);
            return null;
        }
        return mongoTemplate.save(answerEntity);
    }

}

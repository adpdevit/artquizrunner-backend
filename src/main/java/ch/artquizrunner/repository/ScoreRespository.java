package ch.artquizrunner.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.artquizrunner.dao.SequenceDAO;
import ch.artquizrunner.entity.ScoreEntity;
import ch.artquizrunner.exception.SequenceException;

@Repository
public class ScoreRespository {
    private static final String SCORE_SEQ_KEY = "score_seq";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceDAO sequenceDAO;

    public ScoreEntity addScore(ScoreEntity scoreEntity) {
        try {
            scoreEntity.setId(sequenceDAO.getNextSequenceId(SCORE_SEQ_KEY));
        } catch (SequenceException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Could not get next sequence", e);
            return null;
        }
        return mongoTemplate.save(scoreEntity);
    }

    public List<ScoreEntity> getScoresOrderByDescScore(int nbItems) {
        Query query = new Query();
        query.with(Sort.by(Direction.DESC, "score"));
        query.limit(nbItems);
        return mongoTemplate.find(query, ScoreEntity.class);
    }
}

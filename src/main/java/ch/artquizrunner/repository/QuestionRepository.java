package ch.artquizrunner.repository;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.artquizrunner.dao.SequenceDAO;
import ch.artquizrunner.entity.QuestionEntity;
import ch.artquizrunner.exception.SequenceException;

@Repository
public class QuestionRepository {

    private static final String QUESTION_SEQ_KEY = "question_seq";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceDAO sequenceDAO;

    @Autowired
    private AnswerRepository answerRepository;

    public Optional<QuestionEntity> getRandomQuestionEntity() {
        SampleOperation matchStage = Aggregation.sample(1);
        Aggregation aggregation = Aggregation.newAggregation(matchStage);
        AggregationResults<QuestionEntity> output = mongoTemplate.aggregate(aggregation, "question",
                QuestionEntity.class);
        return output.getMappedResults().stream().findAny();
    }

    public QuestionEntity addQuestion(QuestionEntity question) {
        try {
            question.setId(sequenceDAO.getNextSequenceId(QUESTION_SEQ_KEY));
        } catch (SequenceException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Could not get next sequence", e);
            return null;
        }
        question.getOptions().stream().forEach(answerRepository::addAnswer);
        return mongoTemplate.save(question);
    }

    public QuestionEntity getQuestionById(Long id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, QuestionEntity.class);
    }

}

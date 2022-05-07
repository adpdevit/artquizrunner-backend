package ch.artquizrunner.entity;

import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import ch.artquizrunner.model.AnswerOption;

@Document(collection = "question")
public class QuestionEntity {
    @Id
    private Long id;

    private String description;

    @DBRef
    private Set<AnswerOption> options = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AnswerOption> getOptions() {
        return options;
    }

    public void setOptions(Set<AnswerOption> options) {
        this.options = options;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, options);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuestionEntity other = (QuestionEntity) obj;
        return Objects.equals(description, other.description) && Objects.equals(options, other.options);
    }

}

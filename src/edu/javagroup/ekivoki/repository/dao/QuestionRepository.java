package edu.javagroup.ekivoki.repository.dao;

import edu.javagroup.ekivoki.model.Question;
import edu.javagroup.ekivoki.repository.JdbcRepository;

import java.util.Optional;

public interface QuestionRepository extends JdbcRepository<Question> {

    Optional<Question> update(Question question);

    void remove(Long id);
}

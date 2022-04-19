package edu.javagroup.ekivoki.repository.dao;

import edu.javagroup.ekivoki.model.Topic;
import edu.javagroup.ekivoki.repository.JdbcRepository;

import java.util.Optional;

public interface TopicRepository extends JdbcRepository<Topic> {

    Optional<Topic> update(Topic topic);

    void remove();
}

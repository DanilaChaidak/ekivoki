package edu.javagroup.ekivoki.repository.dao;

import edu.javagroup.ekivoki.model.Session;
import edu.javagroup.ekivoki.repository.JdbcRepository;

import java.util.Optional;

public interface SessionRepository extends JdbcRepository<Session> {

    Optional<Session> findBySessionUuid(String sessionUuid);

    void remove();
}

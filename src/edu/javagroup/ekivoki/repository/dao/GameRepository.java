package edu.javagroup.ekivoki.repository.dao;

import edu.javagroup.ekivoki.model.Game;
import edu.javagroup.ekivoki.repository.JdbcRepository;

import java.util.Optional;

public interface GameRepository extends JdbcRepository<Game> {

    Optional<Game> findGameBySessionUuid(String uuid);

    void remove(Long id);
}

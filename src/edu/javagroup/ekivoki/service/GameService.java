package edu.javagroup.ekivoki.service;

import edu.javagroup.ekivoki.model.Game;

import java.util.List;

public interface GameService {

    Game findOne(Long id);

    List<Game> findAll();

    Game create(Game game);

    Game findGameBySessionUuid(String uuid);

    void remove(Long id);
}

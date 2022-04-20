package edu.javagroup.ekivoki.service.impl;

import edu.javagroup.ekivoki.exeption.EntityNotFoundException;
import edu.javagroup.ekivoki.model.Game;
import edu.javagroup.ekivoki.repository.dao.GameRepository;
import edu.javagroup.ekivoki.repository.dao.impl.GameRepositoryImpl;
import edu.javagroup.ekivoki.service.GameService;

import java.util.List;

public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl() {
        this.gameRepository = new GameRepositoryImpl();
    }

    @Override
    public Game findOne(Long id) {
        return gameRepository.findOne(id).orElseThrow(
                () -> new EntityNotFoundException("Game not found by id: " + id)
        );
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game create(Game game) {
        return gameRepository.create(game).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Game findGameBySessionUuid(String uuid) {
        return gameRepository.findGameBySessionUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException("Game not found by session UUID: " + uuid)
        );
    }

    @Override
    public void remove(Long id) {
        gameRepository.remove(id);
    }
}

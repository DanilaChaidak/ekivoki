package edu.javagroup.ekivoki.repository.dao.impl;

import edu.javagroup.ekivoki.connector.ConnectionSingleton;
import edu.javagroup.ekivoki.connector.QuerySingleton;
import edu.javagroup.ekivoki.model.Game;
import edu.javagroup.ekivoki.repository.dao.GameRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GameRepositoryImpl implements GameRepository {

    @Override
    public Optional<Game> findOne(Long id) {
        Optional<Connection> optionalConnection = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (optionalConnection.isPresent()) {
            try (PreparedStatement ps = optionalConnection.get().prepareStatement(queryMap.getQuery("gameFindOneById"))) {
                ps.setLong(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    Game game = new Game();
                    game.setId(resultSet.getLong("id"));
                    game.setSessionId(resultSet.getLong("session_id"));
                    game.setCardId(resultSet.getLong("card_id"));
                    return Optional.of(game);
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Game> findAll() {
        Optional<Connection> optionalConnection = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (optionalConnection.isPresent()) {
            try (Statement statement = optionalConnection.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery("gameFindAll");
                List<Game> result = new ArrayList<>();
                while (resultSet.next()) {
                    Game game = new Game();
                    game.setId(resultSet.getLong("id"));
                    game.setSessionId(resultSet.getLong("session_id"));
                    game.setCardId(resultSet.getLong("card_id"));
                    result.add(game);
                }
                return result;
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Game> create(Game model) {
        Optional<Connection> optionalConnection = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (optionalConnection.isPresent()) {
            try (PreparedStatement ps = optionalConnection.get().prepareStatement(queryMap.getQuery("gameCreate"))) {
                ps.setLong(1, model.getSessionId());
                ps.setLong(2, model.getCardId());
                if (ps.executeUpdate() > 0) {
                    ResultSet resultSet = ps.getGeneratedKeys();
                    if (resultSet.next()) {
                        return findOne(resultSet.getLong(1));
                    }
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Game> findGameBySessionUuid(String uuid) {
        Optional<Connection> optionalConnection = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (optionalConnection.isPresent()) {
            try (PreparedStatement ps = optionalConnection.get().prepareStatement(queryMap.getQuery("gameFindBySessionUuid"))) {
                ps.setString(1, uuid);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    Game game = new Game();
                    game.setId(resultSet.getLong("id"));
                    game.setSessionId(resultSet.getLong("session_id"));
                    game.setCardId(resultSet.getLong("card_id"));
                    return Optional.of(game);
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
        Optional<Connection> optionalConnection = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (optionalConnection.isPresent()) {
            try (PreparedStatement ps = optionalConnection.get().prepareStatement(queryMap.getQuery("gameDeleteById"))) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
    }
}

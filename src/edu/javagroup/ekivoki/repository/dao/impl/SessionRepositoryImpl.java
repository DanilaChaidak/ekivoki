package edu.javagroup.ekivoki.repository.dao.impl;

import edu.javagroup.ekivoki.connector.ConnectionSingleton;
import edu.javagroup.ekivoki.connector.QuerySingleton;
import edu.javagroup.ekivoki.model.Session;
import edu.javagroup.ekivoki.repository.dao.SessionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SessionRepositoryImpl implements SessionRepository {

    @Override
    public Optional<Session> findOne(Long id) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            String queryStr = queryMap.getQuery("sessionFindById");
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryStr)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Session session = new Session();
                    session.setId(resultSet.getLong("id"));
                    session.setSessionUuid(resultSet.getString("session_uuid"));
                    session.setDateCreation(resultSet.getDate("date_creation"));
                    return Optional.of(session);
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Session> findAll() {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery(queryMap.getQuery("sessionFindAll"));
                List<Session> result = new ArrayList<>();
                while (resultSet.next()) {
                    Session session = new Session();
                    session.setId(resultSet.getLong("id"));
                    session.setSessionUuid(resultSet.getString("session_uuid"));
                    session.setDateCreation(resultSet.getDate("date_creation"));
                    result.add(session);
                }
                return result;
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Session> create(Session model) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryMap.getQuery("sessionCreate"), Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, model.getSessionUuid());
                if (preparedStatement.executeUpdate() > 0) {
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
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
    public Optional<Session> findBySessionUuid(String sessionUuid) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            String queryStr = queryMap.getQuery("sessionFindByUUID");
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryStr)) {
                preparedStatement.setString(1, sessionUuid);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Session gameSession = new Session();
                    gameSession.setId(resultSet.getLong("id"));
                    gameSession.setSessionUuid(resultSet.getString("session_uuid"));
                    gameSession.setDateCreation(resultSet.getDate("date_creation"));
                    return Optional.of(gameSession);
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove() {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                statement.executeUpdate(queryMap.getQuery("sessionDeleteByTime"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

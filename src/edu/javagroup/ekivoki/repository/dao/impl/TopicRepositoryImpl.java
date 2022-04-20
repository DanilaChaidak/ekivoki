package edu.javagroup.ekivoki.repository.dao.impl;

import edu.javagroup.ekivoki.connector.ConnectionSingleton;
import edu.javagroup.ekivoki.connector.QuerySingleton;
import edu.javagroup.ekivoki.model.Topic;
import edu.javagroup.ekivoki.repository.dao.TopicRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TopicRepositoryImpl implements TopicRepository {

    @Override
    public Optional<Topic> findOne(Long id) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            String queryStr = queryMap.getQuery("topicFindOneById");
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryStr)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Topic topic = new Topic();
                    topic.setId(resultSet.getLong("id"));
                    topic.setName(resultSet.getString("name"));
                    return Optional.of(topic);
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Topic> findAll() {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery(queryMap.getQuery("topicFindAll"));
                List<Topic> result = new ArrayList<>();
                while (resultSet.next()) {
                    Topic topic = new Topic();
                    topic.setId(resultSet.getLong("id"));
                    topic.setName(resultSet.getString("name"));
                    result.add(topic);
                }
                return result;
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Topic> create(Topic topic) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement ps = connectionOptional.get().prepareStatement(queryMap.getQuery("topicCreate"), Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, topic.getName());
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
    public Optional<Topic> update(Topic topic) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryMap.getQuery("topicUpdate"))) {
                Optional<Topic> optionalTopic = findOne(topic.getId());
                if (optionalTopic.isPresent()) {
                    preparedStatement.setString(1, topic.getName());
                    preparedStatement.executeUpdate();
                    return findOne(topic.getId());
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryMap.getQuery("topicDeleteById"))) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
    }
}

package edu.javagroup.ekivoki.repository.dao.impl;

import edu.javagroup.ekivoki.connector.ConnectionSingleton;
import edu.javagroup.ekivoki.connector.QuerySingleton;
import edu.javagroup.ekivoki.model.Question;
import edu.javagroup.ekivoki.repository.dao.QuestionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionRepositoryImpl implements QuestionRepository {

    @Override
    public Optional<Question> findOne(Long id) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            String queryStr = queryMap.getQuery("questionFindOneById");
            try (PreparedStatement ps = connectionOptional.get().prepareStatement(queryStr)) {
                ps.setLong(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    Question question = new Question();
                    question.setId(resultSet.getLong("id"));
                    question.setName(resultSet.getString("name"));
                    question.setDescription(resultSet.getString("description"));
                    return Optional.of(question);
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Question> findAll() {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery(queryMap.getQuery("questionFindAll"));
                List<Question> result = new ArrayList<>();
                while (resultSet.next()) {
                    Question question = new Question();
                    question.setId(resultSet.getLong("id"));
                    question.setName(resultSet.getString("name"));
                    question.setDescription(resultSet.getString("description"));
                    result.add(question);
                }
                return result;
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Question> create(Question model) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement ps = connectionOptional.get().prepareStatement(queryMap.getQuery("questionCreate"), Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, model.getName());
                ps.setString(2, model.getDescription());
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
    public Optional<Question> update(Question question) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement ps = connectionOptional.get().prepareStatement(queryMap.getQuery("questionUpdate"))) {
                Optional<Question> optionalQuestion = findOne(question.getId());
                if (optionalQuestion.isPresent()) {
                    ps.setString(1, question.getName());
                    ps.setString(2, question.getDescription());
                    ps.executeUpdate();
                    return findOne(question.getId());
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
            try (PreparedStatement ps = connectionOptional.get().prepareStatement(queryMap.getQuery("questionDeleteById"))) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
    }
}

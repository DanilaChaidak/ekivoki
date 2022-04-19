package edu.javagroup.ekivoki.repository.dao.impl;

import edu.javagroup.ekivoki.connector.ConnectionSingleton;
import edu.javagroup.ekivoki.connector.QuerySingleton;
import edu.javagroup.ekivoki.model.Card;
import edu.javagroup.ekivoki.repository.dao.CardRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CardRepositoryImpl implements CardRepository {

    @Override
    public Optional<Card> findOne(Long id) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryMap.getQuery("cardFindById"))) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Card card = new Card();
                    card.setId(resultSet.getLong("id"));
                    card.setTopicId(resultSet.getLong("topic_id"));
                    card.setQuestionId(resultSet.getLong("question_id"));
                    card.setQuestionNumber(resultSet.getInt("question_number"));
                    card.setQuestionText(resultSet.getString("question_text"));
                    card.setLeadTime(resultSet.getInt("lead_time"));
                    card.setDateCreation(resultSet.getDate("date_creation"));
                    card.setLastModified(resultSet.getDate("last_modified"));
                    card.setVersion(resultSet.getLong("version"));
                    return Optional.of(card);
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Card> findAll() {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery("cardFindAll");
                List<Card> result = new ArrayList<>();
                while (resultSet.next()) {
                    Card card = new Card();
                    card.setId(resultSet.getLong("id"));
                    card.setTopicId(resultSet.getLong("topic_id"));
                    card.setQuestionId(resultSet.getLong("question_id"));
                    card.setQuestionNumber(resultSet.getInt("question_number"));
                    card.setQuestionText(resultSet.getString("question_text"));
                    card.setLeadTime(resultSet.getInt("lead_time"));
                    card.setDateCreation(resultSet.getDate("date_creation"));
                    card.setLastModified(resultSet.getDate("last_modified"));
                    card.setVersion(resultSet.getLong("version"));
                    result.add(card);
                }
                return result;
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Card> create(Card model) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryMap.getQuery("cardCreate"))) {
                preparedStatement.setLong(1, model.getTopicId());
                preparedStatement.setLong(2, model.getQuestionId());
                preparedStatement.setLong(3, model.getQuestionNumber());
                preparedStatement.setString(4, model.getQuestionText());
                preparedStatement.setLong(5, model.getLeadTime());
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
    public Optional<Card> findByNumber(int number) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryMap.getQuery("cardFindByNumber"))) {
                preparedStatement.setLong(1, number);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Card card = new Card();
                    card.setId(resultSet.getLong("id"));
                    card.setTopicId(resultSet.getLong("topic_id"));
                    card.setQuestionId(resultSet.getLong("question_id"));
                    card.setQuestionNumber(resultSet.getInt("question_number"));
                    card.setQuestionText(resultSet.getString("question_text"));
                    card.setLeadTime(resultSet.getInt("lead_time"));
                    card.setDateCreation(resultSet.getDate("date_creation"));
                    card.setLastModified(resultSet.getDate("last_modified"));
                    card.setVersion(resultSet.getLong("version"));
                    return Optional.of(card);
                }
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Card> update(Card card) {
        Optional<Connection> connectionOptional = ConnectionSingleton.instance(Optional.empty()).getConnection();
        QuerySingleton queryMap = QuerySingleton.instance(null);
        if (connectionOptional.isPresent()) {
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryMap.getQuery("cardUpdate"))) {
                Optional<Card> optionalCard = findOne(card.getId());
                if (optionalCard.isPresent()) {
                    preparedStatement.setLong(1, card.getTopicId());
                    preparedStatement.setLong(2, card.getQuestionId());
                    preparedStatement.setInt(3, card.getQuestionNumber());
                    preparedStatement.setString(4, card.getQuestionText());
                    preparedStatement.setInt(5, card.getLeadTime());
                    preparedStatement.setLong(6, card.getVersion() + 1);
                    preparedStatement.executeUpdate();
                    return findOne(card.getId());
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
            try (PreparedStatement preparedStatement = connectionOptional.get().prepareStatement(queryMap.getQuery("cardDelete"))) {
                preparedStatement.setLong(1, id);
            } catch (SQLException ex) {
                System.out.println(this.getClass().getSimpleName() + ": " + ex.getMessage());
            }
        }
    }
}

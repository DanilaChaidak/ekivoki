package edu.javagroup.ekivoki.repository.dao;

import edu.javagroup.ekivoki.model.Card;
import edu.javagroup.ekivoki.repository.JdbcRepository;

import java.util.Optional;

public interface CardRepository extends JdbcRepository<Card> {

    Optional<Card> findByNumber(int number);

    Optional<Card> update(Card card);

    void remove(Long id);
}

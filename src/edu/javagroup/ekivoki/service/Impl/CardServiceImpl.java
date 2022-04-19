package edu.javagroup.ekivoki.service.Impl;

import edu.javagroup.ekivoki.model.Card;
import edu.javagroup.ekivoki.repository.dao.CardRepository;
import edu.javagroup.ekivoki.repository.dao.impl.CardRepositoryImpl;
import edu.javagroup.ekivoki.service.CardService;

import java.util.List;

public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl() {
        this.cardRepository = new CardRepositoryImpl();
    }

    @Override
    public Card findOne(Long id) {
        return null;
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public Card save(Card card) {
        return null;
    }

    @Override
    public Card findByNumber(int number) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}

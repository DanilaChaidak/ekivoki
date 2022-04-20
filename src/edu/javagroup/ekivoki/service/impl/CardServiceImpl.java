package edu.javagroup.ekivoki.service.impl;

import edu.javagroup.ekivoki.exeption.EntityNotFoundException;
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
        return cardRepository.findOne(id).orElseThrow(
                () -> new EntityNotFoundException("Card not found bu id: " + id)
        );
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card save(Card card) {
        return card.getId() == null ?
                cardRepository.create(card).orElseThrow(EntityNotFoundException::new) :
                cardRepository.update(card).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Card findByNumber(int number) {
        return cardRepository.findByNumber(number).orElseThrow(
                () -> new EntityNotFoundException("Card not found by number: " + number)
        );
    }

    @Override
    public void remove(Long id) {
        cardRepository.remove(id);
    }
}

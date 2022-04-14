package edu.javagroup.ekivoki.converter;

import edu.javagroup.ekivoki.dto.CardDto;
import edu.javagroup.ekivoki.model.Card;

public interface CardConverter {

    CardDto convert(Card source);

    Card convert(CardDto source);
}

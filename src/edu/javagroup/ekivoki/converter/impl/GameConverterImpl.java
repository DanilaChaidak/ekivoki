package edu.javagroup.ekivoki.converter.impl;

import edu.javagroup.ekivoki.converter.GameConverter;
import edu.javagroup.ekivoki.dto.GameDto;
import edu.javagroup.ekivoki.model.Game;

public class GameConverterImpl implements GameConverter {

    @Override
    public GameDto convert(Game source) {
        if (source == null) {
            return null;
        }
        GameDto target = new GameDto();
        target.setId(source.getId());
        target.setSessionId(source.getSessionId());
        target.setCardId(source.getCardId());
        return target;
    }

    @Override
    public Game convert(GameDto source) {
        if (source == null) {
            return null;
        }
        Game target = new Game();
        target.setId(source.getId());
        target.setSessionId(source.getSessionId());
        target.setCardId(source.getCardId());
        return target;
    }
}

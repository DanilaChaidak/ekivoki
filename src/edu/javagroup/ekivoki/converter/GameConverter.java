package edu.javagroup.ekivoki.converter;

import edu.javagroup.ekivoki.dto.GameDto;
import edu.javagroup.ekivoki.model.Game;

public interface GameConverter {

    GameDto convert(Game source);

    Game convert(GameDto source);
}

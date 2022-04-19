package edu.javagroup.ekivoki.converter;

import edu.javagroup.ekivoki.dto.SessionDto;
import edu.javagroup.ekivoki.model.Session;

public interface SessionConverter {

    SessionDto convert(Session source);

    Session convert(SessionDto source);
}

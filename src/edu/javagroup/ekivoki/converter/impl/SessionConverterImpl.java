package edu.javagroup.ekivoki.converter.impl;

import edu.javagroup.ekivoki.converter.SessionConverter;
import edu.javagroup.ekivoki.dto.SessionDto;
import edu.javagroup.ekivoki.model.Session;
import edu.javagroup.ekivoki.util.DateUtils;

public class SessionConverterImpl implements SessionConverter {

    @Override
    public SessionDto convert(Session source) {
        if (source == null) {
            return null;
        }
        SessionDto target = new SessionDto();
        target.setId(source.getId());
        target.setSessionUuid(source.getSessionUuid());
        target.setDateCreation(DateUtils.getDate(source.getDateCreation()));
        return target;
    }

    @Override
    public Session convert(SessionDto source) {
        if (source == null) {
            return null;
        }
        Session target = new Session();
        target.setId(source.getId());
        target.setSessionUuid(source.getSessionUuid());
        return target;
    }
}

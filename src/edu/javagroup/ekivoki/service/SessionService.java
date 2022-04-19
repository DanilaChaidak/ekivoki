package edu.javagroup.ekivoki.service;

import edu.javagroup.ekivoki.model.Session;

import java.util.List;

public interface SessionService {

    Session findOne(Long id);

    List<Session> findAll();

    Session create(Session gameSession);

    Session findBySessionUuid(String sessionUUID);

    void remove();
}

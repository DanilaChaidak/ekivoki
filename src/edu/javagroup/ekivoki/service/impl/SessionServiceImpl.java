package edu.javagroup.ekivoki.service.impl;

import edu.javagroup.ekivoki.exeption.EntityNotFoundException;
import edu.javagroup.ekivoki.model.Session;
import edu.javagroup.ekivoki.repository.dao.SessionRepository;
import edu.javagroup.ekivoki.repository.dao.impl.SessionRepositoryImpl;
import edu.javagroup.ekivoki.service.SessionService;

import java.util.List;

public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    public SessionServiceImpl() {
        this.sessionRepository = new SessionRepositoryImpl();
    }

    @Override
    public Session findOne(Long id) {
        return sessionRepository.findOne(id).orElseThrow(
                () -> new EntityNotFoundException("Session not found by id: " + id)
        );
    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session create(Session session) {
        return sessionRepository.create(session).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Session findBySessionUuid(String sessionUUID) {
        return sessionRepository.findBySessionUuid(sessionUUID).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void remove() {
        sessionRepository.remove();
    }
}

package edu.javagroup.ekivoki.repository;

import java.util.List;
import java.util.Optional;

public interface JdbcRepository<M> {

    Optional<M> findOne(Long id);

    List<M> findAll();

    Optional<M> create(M model);
}

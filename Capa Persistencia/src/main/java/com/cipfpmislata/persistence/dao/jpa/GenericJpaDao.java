package com.cipfpmislata.persistence.dao.jpa;

import java.util.List;
import java.util.Optional;

public interface GenericJpaDao<T> {
    List<T> findAll();
    long count();
    Optional<T> findById();
    T insert(T jpaEntity);
    T update(T jpaEntity);
    void deleteById(long id);
}

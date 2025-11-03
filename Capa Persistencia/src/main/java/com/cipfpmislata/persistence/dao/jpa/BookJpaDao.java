package com.cipfpmislata.persistence.dao.jpa;

import com.cipfpmislata.persistence.dao.jpa.entity.BookJpaEntity;

import java.util.Optional;

public interface BookJpaDao extends GenericJpaDao<BookJpaEntity>{
    Optional<BookJpaEntity> findByIsbn();
    void deleteByIsbn(String isbn);
}

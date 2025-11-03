package com.cipfpmislata.persistence.dao.jpa;

import com.cipfpmislata.persistence.dao.jpa.entity.AuthorJpaEntity;

import java.util.Optional;

public interface AuthorJpaDao extends GenericJpaDao<AuthorJpaEntity>{
    Optional<AuthorJpaEntity> findBySlug(String slug);
}

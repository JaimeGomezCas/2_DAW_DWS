package com.cipfpmislata.persistence.dao.jpa;

import com.cipfpmislata.persistence.dao.jpa.entity.PublisherJpaEntity;

import java.util.Optional;

public interface Publisher extends GenericJpaDao<PublisherJpaEntity>{
    Optional<PublisherJpaEntity> findBySlug(String slug);
}

package com.cipfpmislata.repository;

import com.cipfpmislata.model.Page;
import com.cipfpmislata.repository.entity.AuthorEntity;
import com.cipfpmislata.repository.entity.PublisherEntity;

import java.util.Optional;

public interface PublisherRepository {
    Page<PublisherEntity> getAll(int page, int size);
    Optional<PublisherEntity> findBySlug(String slug);
    Optional<PublisherEntity > findById(Long id);
    void deleteBySlug(String slug);
    void deleteById(Long id);
    PublisherEntity save(PublisherEntity publisherEntity);
}

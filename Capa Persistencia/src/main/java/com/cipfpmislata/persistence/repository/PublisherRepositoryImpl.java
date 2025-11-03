package com.cipfpmislata.persistence.repository;

import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.domain.repository.entity.PublisherEntity;

import java.util.List;
import java.util.Optional;

public class PublisherRepositoryImpl implements PublisherRepository{
    @Override
    public Optional<PublisherEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<PublisherEntity> findBySlug(String slug) {
        return Optional.empty();
    }

    @Override
    public List<PublisherEntity> findAll(int page, int size) {
        return List.of();
    }

    @Override
    public Optional<PublisherEntity> create(PublisherEntity publisherEntity) {
        return Optional.empty();
    }

    @Override
    public Optional<PublisherEntity> save(PublisherEntity publisherEntity) {
        return Optional.empty();
    }
}

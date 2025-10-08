package es.cesguiro.domain.repository;

import es.cesguiro.domain.repository.entity.PublisherEntity;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {

    Optional<PublisherEntity> findById(Long id);
    Optional<PublisherEntity> findBySlug(String slug);

    List<PublisherEntity> findAll(int page, int size);

    Optional<PublisherEntity> create(PublisherEntity publisherEntity);
    Optional<PublisherEntity> save(PublisherEntity publisherEntity);
}

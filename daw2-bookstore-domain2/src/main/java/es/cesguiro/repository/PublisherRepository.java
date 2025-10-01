package es.cesguiro.repository;

import es.cesguiro.model.Publisher;
import es.cesguiro.repository.entity.BookEntity;
import es.cesguiro.repository.entity.PublisherEntity;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {
    PublisherEntity publisherEntity (PublisherEntity publisherEntity);

    List<PublisherEntity> findAll(int page, int size);

    Optional<PublisherEntity> create(PublisherEntity publisherEntity);
    Optional<PublisherEntity> update(PublisherEntity publisherEntity);
}

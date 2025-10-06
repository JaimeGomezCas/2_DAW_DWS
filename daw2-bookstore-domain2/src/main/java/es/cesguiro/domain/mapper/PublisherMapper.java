package es.cesguiro.domain.mapper;

import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.repository.entity.PublisherEntity;
import es.cesguiro.domain.service.dto.PublisherDto;

public class PublisherMapper {

    private static PublisherMapper INSTANCE;

    private PublisherMapper() {
    }

    public static PublisherMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PublisherMapper();
        }
        return INSTANCE;
    }

    public Publisher fromPublisherEntityToPublisher(PublisherEntity publisherEntity) {

        return new Publisher(
                publisherEntity.name(),
                publisherEntity.slug(),
                0
        );
    }

    public PublisherEntity fromPublisherToPublisherEntity(Publisher publisher) {

        return new PublisherEntity(

                publisher.getId(),
                publisher.getName(),
                publisher.getSlug()
        );
    }

    public PublisherDto fromPublisherToPublisherDto(Publisher publisher) {

        return new PublisherDto(
                publisher.getId(),
                publisher.getName(),
                publisher.getSlug()
        );
    }

    public Publisher fromPublisherDtoToPublisher(PublisherDto publisherDto) {

        return new Publisher(
                publisherDto.name(),
                publisherDto.slug(),
                publisherDto.id()
        );
    }
}

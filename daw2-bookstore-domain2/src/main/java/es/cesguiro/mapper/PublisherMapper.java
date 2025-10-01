package es.cesguiro.mapper;

import es.cesguiro.exception.BusinessException;
import es.cesguiro.model.Publisher;
import es.cesguiro.repository.entity.PublisherEntity;
import es.cesguiro.service.dto.PublisherDto;

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

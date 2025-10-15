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
        if(publisherEntity != null){
            return new Publisher(
                    publisherEntity.name(),
                    publisherEntity.slug(),
                    publisherEntity.id()
            );
        }else return null;
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
        if(publisherDto != null) {
            return new Publisher(
                    publisherDto.name(),
                    publisherDto.slug(),
                    publisherDto.id()
            );
        }else return null;

    }
}

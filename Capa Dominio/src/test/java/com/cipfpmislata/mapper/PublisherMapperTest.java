package com.cipfpmislata.mapper;

import com.cipfpmislata.model.Publisher;
import com.cipfpmislata.repository.entity.PublisherEntity;
import com.cipfpmislata.service.dto.PublisherDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherMapperTest {
    PublisherMapper mapper = PublisherMapper.getInstance();
    Publisher publisher = new Publisher(
            1L,
            "publisher",
            "publisher_slug"
    );
    PublisherDto publisherDto = new PublisherDto(
            1L,
            "publisher",
            "publisher_slug"
    );
    PublisherEntity publisherEntity = new PublisherEntity(
            1L,
            "publisher",
            "publisher_slug"
    );

    @Test
    @DisplayName("Map publisher to publisherDto should return a correct PublisherDto")
    void fromPublisherToPublisherDto() {
        PublisherDto mappedPublisherDto = mapper.fromPublisherToPublisherDto(publisher);

        assertAll(
                ()-> assertEquals(mappedPublisherDto.id(), publisher.getId()),
                ()-> assertEquals(mappedPublisherDto.name(), publisher.getName()),
                ()-> assertEquals(mappedPublisherDto.slug(), publisher.getSlug())
        );
    }

    @Test
    @DisplayName("Map publisher to publisherEntity should return a correct publisherEntity")
    void fromPublisherToPublisherEntity() {
        PublisherEntity mappedPublisherEntity = mapper.fromPublisherToPublisherEntity(publisher);

        assertAll(
                ()-> assertEquals(mappedPublisherEntity.id(), publisher.getId()),
                ()-> assertEquals(mappedPublisherEntity.name(), publisher.getName()),
                ()-> assertEquals(mappedPublisherEntity.slug(), publisher.getSlug())
        );
    }

    @Test
    @DisplayName("Map publisherEntity to publisher should return a correct Publisher")
    void fromPublisherEntityToPublisher() {
        Publisher mappedPublisher = mapper.fromPublisherEntityToPublisher(publisherEntity);

        assertAll(
                ()-> assertEquals(mappedPublisher.getId(), publisherEntity.id()),
                ()-> assertEquals(mappedPublisher.getName(), publisherEntity.name()),
                ()-> assertEquals(mappedPublisher.getSlug(), publisherEntity.slug())
        );
    }

    @Test
    @DisplayName("Map publisherDto to publisher should return a correct Publisher")
    void fromPublisherDtoToPublisher() {
        Publisher mappedPublisher = mapper.fromPublisherDtoToPublisher(publisherDto);

        assertAll(
                ()-> assertEquals(mappedPublisher.getId(), publisherDto.id()),
                ()-> assertEquals(mappedPublisher.getName(), publisherDto.name()),
                ()-> assertEquals(mappedPublisher.getSlug(), publisherDto.slug())
        );
    }

    @Test
    @DisplayName("Mapping a null publisher returns null")
    void allMapperMethodsReturnNull_WhenGivenANullAuthor(){
        assertAll(
                ()-> assertNull(mapper.fromPublisherDtoToPublisher(null)),
                ()-> assertNull(mapper.fromPublisherEntityToPublisher(null)),
                ()-> assertNull(mapper.fromPublisherToPublisherDto(null)),
                ()-> assertNull(mapper.fromPublisherToPublisherEntity(null))
        );
    }
}
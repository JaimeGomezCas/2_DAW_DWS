package es.cesguiro.service.impl;

import es.cesguiro.repository.PublisherRepository;
import es.cesguiro.repository.entity.PublisherEntity;
import es.cesguiro.service.dto.PublisherDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PublisherServiceImplTest {

    @Mock
    PublisherRepository publisherRepository;

    @InjectMocks
    PublisherServiceImpl publisherServiceImpl;

    @Nested
    class TestGetBySlug{
        @Test
        @DisplayName("get by slug when publisher exists")
        void getBySlug_WhenPublisherExists(){
            PublisherEntity publisher = new PublisherEntity(1, "Cervantes" , "cervantes");
            when(publisherRepository.findBySlug("cervantes")).thenReturn(Optional.of(publisher));

            PublisherDto publisherDto = publisherServiceImpl.getBySlug("cervantes");

            assertEquals(publisher.slug(), publisherDto.slug());


        }
    }
//    @Test
//    @DisplayName("Update publisher")
//    void updatePublisher() {
//
//
//    }
//
//    @Test
//    @DisplayName('Update non existing publisher')
//
//    @Test
//    @DisplayName("Update slug")



}
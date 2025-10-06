package es.cesguiro.service.impl;

import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.domain.repository.entity.PublisherEntity;
import es.cesguiro.domain.service.dto.PublisherDto;
import es.cesguiro.domain.service.impl.PublisherServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherServiceImpl publisherServiceImpl;

    @Test
    @DisplayName("getAll when there are publishers")
    void getAll_WhenThereArePublishers(){
        PublisherEntity publisher = new PublisherEntity(1, "Ben", "ben");
        ArrayList<PublisherEntity> publisherList = new ArrayList<>();
        publisherList.add(publisher);

        when(publisherRepository.findAll(1,1)).thenReturn((publisherList));

        assertNotNull(publisherServiceImpl.getAll(1,1));
    }

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

    @Test
    @DisplayName("create when received publisher is null")
    void create_WhenNull(){
        PublisherEntity publisher = null;
        when(publisherRepository.findBySlug("cervantes")).thenReturn(Optional.of(publisher));

        PublisherDto publisherDto = publisherServiceImpl.getBySlug("cervantes");

        assertEquals(publisher.slug(), publisherDto.slug());
    }

    @ParameterizedTest
    @DisplayName("create when received publisher is null should throw validation error")
    @CsvSource({
            "1, 'Cervantes', 'cervantes'",
            "1, 'Cervantes', cerewijsknfdkjsgnkjsgsjoisjgoi34joif",
            "1, null, 'cervantes'",
    })
    void create_WhenSomeValueIsWrong(long id, String name, String slug){
        PublisherEntity publisher = new PublisherEntity(id, name, slug);
        PublisherDto publisherDto = new PublisherDto(id, name, slug);

        when(publisherRepository.create(publisher)).thenReturn(Optional.of(publisher));

        assertThrows(ValidationException.class, () -> DtoValidator.validate(publisherDto));
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
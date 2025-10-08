package es.cesguiro.domain.service.impl;

import es.cesguiro.data.loader.PublishersDataLoader;
import es.cesguiro.domain.exception.ValidationException;
import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.domain.repository.entity.PublisherEntity;
import es.cesguiro.domain.service.dto.PublisherDto;
import es.cesguiro.domain.service.impl.PublisherServiceImpl;
import es.cesguiro.domain.validation.hibernate_validator.DtoValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherServiceImpl publisherServiceImpl;

    private static List<Publisher> publishers;
    private static List<PublisherDto> publisherDtos;
    private static List<PublisherEntity> publisherEntities;

    @BeforeAll
    static void setUp() {
        PublishersDataLoader publishersDataLoader = new PublishersDataLoader();
        publishers = publishersDataLoader.loadPublishersFromCSV();
        publisherDtos = publishersDataLoader.loadPublisherDtosFromCSV();
        publisherEntities = publishersDataLoader.loadPublisherEntitiesFromCSV();
    }

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

    @ParameterizedTest
    @DisplayName("create when data is wrong should throw formatting error")
    @CsvSource({
            "1, 'Cervantes', '3748626384JBAJDBH'",
            "1, , 'cervantes'",
    })
    void create_WhenSomeValueIsWrong(long id, String name, String slug){
        PublisherEntity publisher = new PublisherEntity(id, name, slug);
        PublisherDto publisherDto = new PublisherDto(id, name, slug);

        when(publisherRepository.create(publisher)).thenReturn(Optional.of(publisher));

        publisherServiceImpl.create(publisherDto);

        assertThrows(ValidationException.class, () -> DtoValidator.validate(publisherDto));
    }


    @Test
    @DisplayName("Update publisher")
    void updatePublisher(){
        PublisherEntity publisherEntity = publisherEntities.getFirst();
        PublisherDto publisherDto = publisherDtos.getFirst();
        PublisherEntity updatePublisherEntity = new PublisherEntity(
                publisherEntity.id(),
                "Updated Name",
                "updated-slug"
        );
        PublisherDto expected = new PublisherDto(
                publisherDto.id(),
                "Updated Name",
                "updated-slug");

        when(publisherRepository.findById(publisherDto.id())).thenReturn(Optional.of(publisherEntity));
        when(publisherRepository.save(publisherEntity)).thenReturn(Optional.of(updatePublisherEntity));

        PublisherDto reusult = publisherServiceImpl.update(publisherDto);

        assertAll(
                () -> assertNotNull(reusult),
                () -> assertEquals(expected.id(), reusult.id()),
                () -> assertEquals(expected.name(), reusult.name()),
                () -> assertEquals(expected.slug(), reusult.slug())
        );

        verify(publisherRepository).save(publisherEntity);
    }
//
//    @Test
//    @DisplayName('Update non existing publisher')
//
//    @Test
//    @DisplayName("Update slug")
//
//    @Test
//    @DisplayName("Create but id already exists")
//
//    @Test
//    @DisplayName("Create but Slug already exists")
//
//    @Test
//    @DisplayName("Update Slug already exists and id is not the same")
}
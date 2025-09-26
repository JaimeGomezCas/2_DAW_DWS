package es.cesguiro.service.impl;

import es.cesguiro.repository.PublisherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class PublisherServiceImplTest {

    @Mock
    PublisherRepository publisherRepository;

    @InjectMocks

    @Test
    @DisplayName("Update publisher")
    void updatePublisher() {
        verify(publisherRepository.save());
        assertTrue(true);

    }

    @Test
    @DisplayName('Update non existing publisher')

    @Test
    @DisplayName("Update slug")



}
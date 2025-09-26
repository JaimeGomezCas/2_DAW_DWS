package es.cesguiro.service.impl;

import es.cesguiro.exception.BusinessException;
import es.cesguiro.exception.ResourceNotFoundException;
import es.cesguiro.model.Book;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.entity.AuthorEntity;
import es.cesguiro.repository.entity.BookEntity;
import es.cesguiro.repository.entity.PublisherEntity;
import es.cesguiro.service.dto.BookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    @DisplayName("getAll should return list of books")
    void getAll_ShouldReturnListOfBooks() {
        // Arrange
        int page = 0;
        int size = 10;

        // Mock books
        BookEntity bookEntity1 = new BookEntity(
                "123",
                "TitleEs1",
                "TitleEn1",
                "SynopsisEs1",
                "SynopsisEn1",
                new BigDecimal("10.00"),
                5,
                "cover1.jpg", LocalDate.of(2020, 1, 1),
                null,
                null
        );
        BookEntity bookEntity2 = new BookEntity(
                "456",
                "TitleEs2",
                "TitleEn2",
                "SynopsisEs2",
                "SynopsisEn2",
                new BigDecimal("15.00"),
                10,
                "cover2.jpg", LocalDate.of(2021, 6, 15),
                null,
                null
        );
        List<BookEntity> bookEntities = List.of(bookEntity1, bookEntity2);
        when(bookRepository.findAll(page, size)).thenReturn(bookEntities);


        // Act
        List<BookDto> result = bookServiceImpl.getAll(page, size);

        // Assert
        assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(2, result.size(), "Result size should be 2"),
                () -> assertEquals("123", result.get(0).isbn(), "First book ISBN should match"),
                () -> assertEquals("456", result.get(1).isbn(), "Second book ISBN should match")
        );

        // Verify interaction with mock
        Mockito.verify(bookRepository).findAll(page, size);
    }

    // test getByIsbn when book exists
    @Test
    void getByIsbn_WhenBookExists(){
        BookEntity bookEntity1 = new BookEntity(
                "123",
                "TitleEs1",
                "TitleEn1",
                "SynopsisEs1",
                "SynopsisEn1",
                new BigDecimal("10.00"),
                5,
                "cover1.jpg", LocalDate.of(2020, 1, 1),
                null,
                null

        );

        when(bookRepository.findByIsbn("123")).thenReturn(Optional.of(bookEntity1));

        BookDto libroDevuelto = bookServiceImpl.getByIsbn("123");

        assertAll(
                ()-> assertNotNull(libroDevuelto)
        );


    }

    // test getByIsbn when book exists



    // test getByIsbn when book does not exist

    @Test
    @DisplayName("Cuando uso getByIsbn y el bookEntity no existe, lanza ResourceNotFoundException")
    void getByIsbn_BookRepositoryDoesNotExist_ThrowsResourceNotFoundException() { // Este test tiene 2 errores

        // Arrange
        BookDto bookDto = null;
        when(bookRepository.findByIsbn("999")).thenReturn(Optional.empty());

        when(bookServiceImpl.getByIsbn(null)).thenThrow(new ResourceNotFoundException("Book not found"));
        // Act

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> bookServiceImpl.getByIsbn(null));
        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> bookServiceImpl.getByIsbn("999"));

    }



    // test create book
    @Test
    @DisplayName("Crear un libro que no exista previamente sin errores")
    void create_ShouldCreateABook (){

    }

    // test update book

    @Test
    @DisplayName("Cambiar un libro existente por otro libro existente sin errores")
    void metodoUpdate_DeBookServiceImpl_DebeCambiar(){

        // Arrange
        BookDto bookDto = new BookDto(
                "123",
                "NuevoTituloEs",
                "NuevoTituloEn",
                "NuevaSinopsisEs",
                "NuevaSinopsisEn",
                new BigDecimal("20.00"),
                8,
                null,
                "nueva_cover.jpg",
                LocalDate.of(2022, 2, 2),
                null,
                null
        );
        // Simula el mapeo de DTO a entidad (esto normalmente lo haría el BookMapper)
        BookEntity bookEntity = new BookEntity(
                "123",
                "NuevoTituloEs",
                "NuevoTituloEn",
                "NuevaSinopsisEs",
                "NuevaSinopsisEn",
                new BigDecimal("20.00"),
                8,
                "nueva_cover.jpg",
                LocalDate.of(2022, 2, 2),
                null,
                null
        );


        // Simula que el repositorio devuelve la entidad actualizada
        when(bookRepository.update(Mockito.any(BookEntity.class))).thenReturn(Optional.of(bookEntity));

        // Act
        BookDto updatedBook = bookServiceImpl.update(bookDto);

        // Assert

        assertNotNull(updatedBook);
        assertEquals("123", updatedBook.isbn());
        assertEquals("NuevoTituloEs", updatedBook.titleEs());
        assertEquals("NuevoTituloEn", updatedBook.titleEn());
        assertEquals("NuevaSinopsisEs", updatedBook.synopsisEs());
        assertEquals("NuevaSinopsisEn", updatedBook.synopsisEn());
        assertEquals(new BigDecimal("20.00"), updatedBook.price());
        assertEquals(8, updatedBook.discountPercentage());
        assertEquals("nueva_cover.jpg", updatedBook.cover());
        assertEquals(LocalDate.of(2022, 2, 2), updatedBook.publicationDate());
        assertNull(updatedBook.publisher());
        assertNull(updatedBook.authors());

    }

    // test create book with existing isbn

    // test create book with invalid data

    // test create book with non-existing authors

    // .....

}
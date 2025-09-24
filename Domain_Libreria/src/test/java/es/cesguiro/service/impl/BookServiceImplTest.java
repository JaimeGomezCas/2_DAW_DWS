package es.cesguiro.service.impl;

import es.cesguiro.exception.ResourceNotFoundException;
import es.cesguiro.model.Author;
import es.cesguiro.model.Book;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.entity.AuthorEntity;
import es.cesguiro.repository.entity.BookEntity;
import es.cesguiro.repository.entity.PublisherEntity;
import es.cesguiro.service.dto.AuthorDto;
import es.cesguiro.service.dto.BookDto;
import es.cesguiro.service.dto.PublisherDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        AuthorEntity mariano = new AuthorEntity("Mariano",
                "Español",
                "Un grande",
                "A Big",
                1900,
                2000,
                "mariano");
        AuthorEntity paolo = new AuthorEntity("Paolo",
                "Español",
                "Un peque",
                "A small",
                1800,
                2000,
                "paolo");
        List<AuthorEntity> autores = List.of(mariano, paolo);
        PublisherEntity publisher = new PublisherEntity("Rubio", "rubio");

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
                publisher,
                autores
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
                publisher,
                autores
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
    @Nested
    class TestGetIsbn {
        AuthorEntity mariano = new AuthorEntity("Mariano",
                "Español",
                "Un grande",
                "A Big",
                1900,
                2000,
                "mariano");
        AuthorEntity paolo = new AuthorEntity("Paolo",
                "Español",
                "Un peque",
                "A small",
                1800,
                2000,
                "paolo");
        List<AuthorEntity> autores = List.of(mariano, paolo);
        PublisherEntity publisher = new PublisherEntity("Rubio", "rubio");
        BookEntity bookEntity1 = new BookEntity(
                "123",
                "TitleEs1",
                "TitleEn1",
                "SynopsisEs1",
                "SynopsisEn1",
                new BigDecimal("10.00"),
                5,
                "cover1.jpg", LocalDate.of(2020, 1, 1),
                publisher,
                autores
        );

        @Test
        @DisplayName("getByIsbn should return an existing bookDto when the book exists")
        void getByIsbn_ShouldReturnABook_WhenBookExist(){

            when(bookRepository.findByIsbn(bookEntity1.isbn())).thenReturn(Optional.of(bookEntity1));
            BookDto bookDto = bookServiceImpl.getByIsbn("123");
            assertAll(
                    ()-> assertNotNull(bookDto),
                    ()-> assertEquals("123", bookDto.isbn())
            );
            Mockito.verify(bookRepository).findByIsbn("123");
        }

        // test getByIsbn when book does not exist
        @Test
        @DisplayName("")
        void getByIsbn_ShouldReturnException_WhenBookDoesntExist(){
            String isbn = "LOL ESTA ISBN NO EXISTE";
            when(bookRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class,
                    ()-> bookServiceImpl.getByIsbn(isbn)
            );

        }
    }

    @Nested
    class TestCreateBook{
        //DATOS PARA LOS TEST
        String isbn = "123";
        String tituloEs = "Quijote";
        String tituloEn = "Quijote en ingles";
        String synopsisEs = "Synopsis español";
        String synopsisEn = "Synopsis ingles";
        BigDecimal precioBaseCorrecto = BigDecimal.TEN;
        double porcentajeDescuento = 12.5;
        String cover = "tapa dura";
        LocalDate fechaPublicacion = LocalDate.now();
        PublisherDto publisherDto = new PublisherDto(
                "Pedro",
                "pedro"
        );
        AuthorDto mariano = new AuthorDto("Mariano",
                "Español",
                "Un grande",
                "A Big",
                1900,
                2000,
                "mariano");
        AuthorDto paolo = new AuthorDto("Paolo",
                "Español",
                "Un peque",
                "A small",
                1800,
                2000,
                "paolo");
        List<AuthorDto> autores = List.of(mariano, paolo);



        //Probar que se crea correctamente cuando se introducen todos los datos bien
        //Probar crear un libro con un isbn que ya existe
        //Probar a crear un libro sin autores
        //Probar a crear un libro con un descuento negativo
        //Probar a crear un libro con un descuento de 100 0 mas
        //Probar a crear un libro con precio base negativo

    }



    // test create book


    // test create book with existing isbn

    // test create book with invalid data

    // test create book with non-existing authors

    // .....

}
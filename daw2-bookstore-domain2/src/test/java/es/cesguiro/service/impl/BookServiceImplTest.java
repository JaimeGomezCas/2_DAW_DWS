package es.cesguiro.service.impl;

import es.cesguiro.exception.BusinessException;
import es.cesguiro.exception.ResourceNotFoundException;
import es.cesguiro.mapper.BookMapper;
import es.cesguiro.model.Book;
import es.cesguiro.model.Publisher;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.entity.AuthorEntity;
import es.cesguiro.repository.entity.BookEntity;
import es.cesguiro.repository.entity.PublisherEntity;
import es.cesguiro.service.dto.BookDto;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
import static org.mockito.ArgumentMatchers.any;
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
                new PublisherEntity(1,"Pepe", "pepe"),
                List.of()

        );

        when(bookRepository.findByIsbn("123")).thenReturn(Optional.of(bookEntity1));

        BookDto libroDevuelto = bookServiceImpl.getByIsbn("123");

        assertAll(
                ()-> assertNotNull(libroDevuelto)
        );


    }

    // test getByIsbn when book exists
    @Test
    @DisplayName("getByIsbn devuelve el libro cuando existe")
    void getByIsbn_WhenBookExists_ReturnsBookDto() {
        // Arrange
        BookEntity bookEntity = new BookEntity(
                "123",
                "TituloEs",
                "TituloEn",
                "SinopsisEs",
                "SinopsisEn",
                new BigDecimal("10.0"),
                5,
                "cover.jpg",
                LocalDate.of(2020, 1, 1),
                new PublisherEntity(1,"Pepe", "pepe"),
                List.of()
        );
        when(bookRepository.findByIsbn("123")).thenReturn(Optional.of(bookEntity));

        // Act
        BookDto result = bookServiceImpl.getByIsbn("123");

        // Assert
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("123", result.isbn()),
                () -> assertEquals("TituloEs", result.titleEs()),
                () -> assertEquals("TituloEn", result.titleEn()),
                () -> assertEquals("SinopsisEs", result.synopsisEs()),
                () -> assertEquals("SinopsisEn", result.synopsisEn()),
                () -> assertEquals(new BigDecimal("9.50"), result.price()),
                () -> assertEquals(5, result.discountPercentage()),
                () -> assertEquals("cover.jpg", result.cover()),
                () -> assertEquals(LocalDate.of(2020, 1, 1), result.publicationDate())
        );
    }


    // test getByIsbn when book does not exist

    @Test
    @DisplayName("Cuando uso getByIsbn y el bookEntity no existe, lanza ResourceNotFoundException")
    void getByIsbn_BookRepositoryDoesNotExist_ThrowsResourceNotFoundException() { // Este test tiene 2 errores

        // Arrange
        when(bookServiceImpl.findByIsbn("999")).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> bookServiceImpl.getByIsbn("999"));

    }



    // test create book
    @Test
    @DisplayName("Crear un libro que no exista previamente sin errores")
    void create_ShouldCreateABook (){
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

    }

    // test update book

    @Test
    @DisplayName("Cambiar un libro existente por otro libro existente sin errores")
    void metodoUpdate_DeBookServiceImpl_DebeCambiar(){


        // Simula el mapeo de DTO a entidad (esto normalmente lo haría el BookMapper)
        BookEntity bookEntitySinActualizar = new BookEntity(
                "123",
                "Titulo NOrmal",
                "NuevoTituloEn",
                "NuevaSinopsisEs",
                "NuevaSinopsisEn",
                new BigDecimal("20.00"),
                8,
                "nueva_cover.jpg",
                LocalDate.of(2022, 2, 2),
                new PublisherEntity( 1L,"Pepe","pepe"),
                List.of()
        );
        BookEntity bookEntityActualizado = new BookEntity(
                "ACTUALIZADO",
                "ACTUALIZADO",
                "ACTUALIZADO",
                "ACTUALIZADO",
                "ACTUALIZADO",
                new BigDecimal("20.00"),
                8,
                "nueva_cover.jpg",
                LocalDate.of(2022, 2, 2),
                new PublisherEntity( 1L,"Pepe","pepe"),
                List.of()
        );



        // Simula que el repositorio devuelve la entidad actualizada
        when(bookRepository.findByIsbn("123")).thenReturn(Optional.of(bookEntitySinActualizar));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(Optional.of(bookEntityActualizado));
        BookMapper mapper = BookMapper.getInstance();
        Book libroSinActualizar = mapper.fromBookEntityToBook(bookEntitySinActualizar);
        BookDto libroDtoSinActualizar = mapper.fromBookToBookDto(libroSinActualizar);
        // Act
        BookDto updatedBook = bookServiceImpl.update(libroDtoSinActualizar);

        // Assert
        assertAll(
                ()-> assertNotNull(updatedBook),
                ()-> assertEquals("ACTUALIZADO", updatedBook.isbn()),
                ()->assertEquals("ACTUALIZADO", updatedBook.titleEs()),
                ()-> assertEquals("ACTUALIZADO", updatedBook.titleEn()),
                ()-> assertEquals("ACTUALIZADO", updatedBook.synopsisEs()),
                ()->assertEquals("ACTUALIZADO", updatedBook.synopsisEn())
        );
    }

    // test create book with existing isbn
    @Test
    @DisplayName("Cuando se mete un isbn nuevo que coincide con el de un libro existente, debe dar error BusinessException (Lógica de Negocio) (El error se debe validar en la capa de Libro)")
    void metodoCreate_DeBookServiceImpl_DebeDarError_siElIsbnDadoExiste() {
        // Arrange
        BookDto bookDto = new BookDto(
                "123",
                "TituloExistente",
                "TituloExistenteEn",
                "SinopsisExistente",
                "SinopsisExistenteEn",
                new BigDecimal("10.00"),
                5,
                null,
                "cover_existente.jpg",
                LocalDate.of(2020, 1, 1),
                null,
                null
        );
        BookEntity bookEntityExistente = new BookEntity(
                "123",
                "TituloExistente",
                "TituloExistenteEn",
                "SinopsisExistente",
                "SinopsisExistenteEn",
                new BigDecimal("10.00"),
                5,
                "cover_existente.jpg",
                LocalDate.of(2020, 1, 1),
                null,
                null
        );
        when(bookRepository.findByIsbn("123")).thenReturn(Optional.of(bookEntityExistente));

        // Act & Assert
        assertThrows(BusinessException.class, () -> bookServiceImpl.create(bookDto));
    }

    // test create book with invalid data | Hay que tener en cuenta que aquí comprobaremos to.do lo relacionado con la base de datos

   /* @Test
    @DisplayName("Usando el CSV para test parametrizados, y teniendo en cuenta que aquí solo vamos a trabajar con datos relacionados con la base de datos" +
            "De forma general que haya datos en la base de datos repetidos que no se pueden repetir (BusinessException)" +
                "Cosas muy concretas como que la fecha de publicación del libro no puede ser anterior al nacimiento de su propio autor"   */


    @ParameterizedTest
    @CsvSource({
            // isbn duplicado
            "'123','Titulo','TituloEn','Sinopsis','SinopsisEn',10.00,5,'cover.jpg',2020-01-01",
            // sinopsis duplicada
            "'456','Titulo','TituloEn','SinopsisDuplicada','SinopsisEn',12.00,10,'cover2.jpg',2021-05-01",
            // cover duplicada
            "'789','Titulo','TituloEn','Sinopsis','SinopsisEn',15.00,20,'cover_duplicada.jpg',2022-03-01"
    })
    @DisplayName("No debe permitir datos duplicados: isbn, sinopsis o cover")
    void createBook_WithDuplicatedIsbnSynopsisOrCover_ShouldThrowBusinessException(
            String isbn, String titleEs, String titleEn, String synopsisEs, String synopsisEn,
            BigDecimal price, int discount, String cover, LocalDate publicationDate) {

        BookDto bookDto = new BookDto(
                isbn, titleEs, titleEn, synopsisEs, synopsisEn, price, discount, null, cover, publicationDate, null, null
        );

        // Simula que existe un libro con el mismo isbn, sinopsis o cover
        BookEntity existingBook = new BookEntity(
                isbn, titleEs, titleEn, synopsisEs, synopsisEn, price, discount, cover, publicationDate, null, null
        );

        // Mock para isbn
        when(bookRepository.findByIsbn(isbn)).thenReturn(Optional.of(existingBook));
        // Mock para sinopsis en Español
        when(bookRepository.findBySynopsisEs(synopsisEs)).thenReturn(Optional.of(existingBook));
        // Mock para sinopsis en Ingles
        when(bookRepository.findBySynopsisEn(synopsisEn)).thenReturn(Optional.of(existingBook));
        // Mock para cover
        when(bookRepository.findByCover(cover)).thenReturn(Optional.of(existingBook));

        assertThrows(BusinessException.class, () -> bookServiceImpl.create(bookDto));
    }

    @Test
    @DisplayName("Find by name should return a list of bookDto that shares the title")
    void findByName_ShouldReturnAListOfBookDto(){
        String tituloBuscado = "El";

        BookDto bookDto1 = new BookDto(
                "123",
                "El imperio final",
                "the final noseque",
                "SinopsisExistente",
                "SinopsisExistenteEn",
                new BigDecimal("10.00"),
                5,
                null,
                "cover_existente.jpg",
                LocalDate.of(2020, 1, 1),
                null,
                null
        );
        BookDto bookDto2 = new BookDto(
                "123",
                "El quijote",
                "The quijote",
                "SinopsisExistente",
                "SinopsisExistenteEn",
                new BigDecimal("10.00"),
                5,
                null,
                "cover_existente.jpg",
                LocalDate.of(2020, 1, 1),
                null,
                null
        );
        BookMapper bookMapper  = BookMapper.getInstance();
        Book book2 = bookMapper.fromBookDtoToBook(bookDto2);
        Book book1 = bookMapper.fromBookDtoToBook(bookDto1);
        BookEntity bookEntity2 = bookMapper.fromBookToBookEntity(book2);
        BookEntity bookEntity1 = bookMapper.fromBookToBookEntity(book1);
        List<BookEntity> librosConElTituloBuscado = List.of(bookEntity1, bookEntity2);
        when(bookRepository.findByName("El")).thenReturn(librosConElTituloBuscado);

        assertAll(
                ()-> assertEquals(2, librosConElTituloBuscado.size())
        );

    }


    // .....

}
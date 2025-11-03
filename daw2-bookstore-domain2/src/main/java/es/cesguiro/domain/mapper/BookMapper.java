package es.cesguiro.domain.mapper;

import es.cesguiro.domain.exception.BusinessException;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.repository.entity.BookEntity;
import es.cesguiro.domain.service.dto.BookDto;

public class BookMapper {

    private static BookMapper INSTANCE;

    private BookMapper() {
    }

    public static BookMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookMapper();
        }
        return INSTANCE;
    }

    public Book fromBookEntityToBook(BookEntity bookEntity) {
        if (bookEntity == null) {
            return null;
        }
        return new Book(
                bookEntity.id(),
                bookEntity.isbn(),
                bookEntity.titleEs(),
                bookEntity.titleEn(),
                bookEntity.synopsisEs(),
                bookEntity.synopsisEn(),
                bookEntity.basePrice(),
                bookEntity.discountPercentage(),
                bookEntity.cover(),
                bookEntity.publicationDate(),
                bookEntity.publisher()!=null? PublisherMapper.getInstance().fromPublisherEntityToPublisher(bookEntity.publisher()) : null,
                bookEntity.authors()!=null? bookEntity.authors().stream().map(AuthorMapper.getInstance()::fromAuthorEntityToAuthor).toList() : null
        );
    }

    public BookEntity fromBookToBookEntity(Book book) {
        if (book == null) {
            return null;
        }
        return new BookEntity(
                book.getId(),
                book.getIsbn(),
                book.getTitleEs(),
                book.getTitleEn(),
                book.getSynopsisEs(),
                book.getSynopsisEn(),
                book.getBasePrice(),
                book.getDiscountPercentage(),
                book.getCover(),
                book.getPublicationDate(),
                book.getPublisher()!=null? PublisherMapper.getInstance().fromPublisherToPublisherEntity(book.getPublisher()) : null,
                book.getAuthors()!=null? book.getAuthors().stream().map(AuthorMapper.getInstance()::fromAuthorToAuthorEntity).toList() : null
        );
    }

    public BookDto fromBookToBookDto(Book book) {
        if (book == null) {
            throw new BusinessException("Cannot map null Book to BookDto");
        }
        return new BookDto(
                book.getId(),
                book.getIsbn(),
                book.getTitleEs(),
                book.getTitleEn(),
                book.getSynopsisEs(),
                book.getSynopsisEn(),
                book.getBasePrice(),
                book.getDiscountPercentage(),
                book.calculateFinalPrice(),
                book.getCover(),
                book.getPublicationDate(),
                book.getPublisher()!=null? PublisherMapper.getInstance().fromPublisherToPublisherDto(book.getPublisher()) : null,
                book.getAuthors()!=null? book.getAuthors().stream().map(AuthorMapper.getInstance()::fromAuthorToAuthorDto).toList() : null
        );
    }


    public Book fromBookDtoToBook(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }
        return new Book(
                bookDto.id(),
                bookDto.isbn(),
                bookDto.titleEs(),
                bookDto.titleEn(),
                bookDto.synopsisEs(),
                bookDto.synopsisEn(),
                bookDto.basePrice(),
                bookDto.discountPercentage(),
                bookDto.cover(),
                bookDto.publicationDate(),
                bookDto.publisher()!=null? PublisherMapper.getInstance().fromPublisherDtoToPublisher(bookDto.publisher()) : null,
                bookDto.authors()!=null? bookDto.authors().stream().map(AuthorMapper.getInstance()::fromAuthorDtoToAuthor).toList() : null
        );
    }
}

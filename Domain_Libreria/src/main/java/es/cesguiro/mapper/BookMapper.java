package es.cesguiro.mapper;

import es.cesguiro.exception.BusinessException;
import es.cesguiro.model.Book;
import es.cesguiro.repository.entity.BookEntity;
import es.cesguiro.service.dto.BookDto;

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
            throw new BusinessException("BookEntity cannot be null");
        } else if (bookEntity.authors().isEmpty()) {
            throw new BusinessException("A book has to have at least one author");
        } else if (bookEntity.discountPercentage() < 0) {
            throw new BusinessException("Discounts cannot be negative");
        }
        return new Book(
                bookEntity.isbn(),
                bookEntity.titleEs(),
                bookEntity.titleEn(),
                bookEntity.synopsisEs(),
                bookEntity.synopsisEn(),
                bookEntity.basePrice(),
                bookEntity.discountPercentage(),
                bookEntity.cover(),
                bookEntity.publicationDate(),
                PublisherMapper.getInstance().fromPublisherEntityToPublisher(bookEntity.publisher()),
                bookEntity.authors().stream().map(AuthorMapper.getInstance()::fromAuthorEntityToAuthor).toList()
        );
    }

    public BookEntity fromBookToBookEntity(Book book) {
        if (book == null) {
            throw new BusinessException("Book cannot be null");
        } else if (book.getAuthors().isEmpty()) {
            throw new BusinessException("Book cannot be null");
        } else if (book.getDiscountPercentage() < 0) {
            throw new BusinessException("Discounts cannot be negative");
        }
        return new BookEntity(
                book.getIsbn(),
                book.getTitleEs(),
                book.getTitleEn(),
                book.getSynopsisEs(),
                book.getSynopsisEn(),
                book.getBasePrice(),
                book.getDiscountPercentage(),
                book.getCover(),
                book.getPublicationDate(),
                PublisherMapper.getInstance().fromPublisherToPublisherEntity(book.getPublisher()),
                book.getAuthors().stream().map(AuthorMapper.getInstance()::fromAuthorToAuthorEntity).toList()
        );
    }

    public BookDto fromBookToBookDto(Book book) {
        if (book == null) {
            throw new BusinessException("BookDto cannot be null");
        } else if (book.getDiscountPercentage() < 0) {
            throw new BusinessException("Discounts cannot be negative");
        } else if (book.getAuthors().isEmpty()) {
            throw new BusinessException("Book cannot be null");
        }
        return new BookDto(
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
                PublisherMapper.getInstance().fromPublisherToPublisherDto(book.getPublisher()),
                book.getAuthors().stream().map(AuthorMapper.getInstance()::fromAuthorToAuthorDto).toList()
        );
    }


    public Book fromBookDtoToBook(BookDto bookDto) {
        if (bookDto == null) {
            throw new BusinessException("BookDto cannot be null");
        } else if (bookDto.discountPercentage() < 0) {
            throw new BusinessException("Discounts cannot be negative");
        }else if (bookDto.authors().isEmpty()) {
            throw new BusinessException("Book cannot be null");
        }
        return new Book(
                bookDto.isbn(),
                bookDto.titleEs(),
                bookDto.titleEn(),
                bookDto.synopsisEs(),
                bookDto.synopsisEn(),
                bookDto.basePrice(),
                bookDto.discountPercentage(),
                bookDto.cover(),
                bookDto.publicationDate(),
                PublisherMapper.getInstance().fromPublisherDtoToPublisher(bookDto.publisher()),
                bookDto.authors().stream().map(AuthorMapper.getInstance()::fromAuthorDtoToAuthor).toList()
        );
    }



}

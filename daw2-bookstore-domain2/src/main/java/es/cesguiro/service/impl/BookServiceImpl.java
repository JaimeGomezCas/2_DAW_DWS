package es.cesguiro.service.impl;

import es.cesguiro.exception.ResourceNotFoundException;
import es.cesguiro.mapper.BookMapper;
import es.cesguiro.model.Book;
import es.cesguiro.repository.entity.BookEntity;
import es.cesguiro.service.dto.BookDto;
import es.cesguiro.exception.BusinessException;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.service.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> getAll(int page, int size) {
            return bookRepository
                    .findAll(page, size)
                    .stream()
                    .map(BookMapper.getInstance()::fromBookEntityToBook)
                    .map(BookMapper.getInstance()::fromBookToBookDto)
                    .toList();
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        return bookRepository
                .findByIsbn(isbn)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book with isbn " + isbn + " not found"));
    }

    @Override
    public Optional<BookDto> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        Book book = BookMapper.getInstance().fromBookDtoToBook(bookDto);
        BookEntity bookEntity = BookMapper.getInstance().fromBookToBookEntity(book);
        Book bookCreated = BookMapper.getInstance().fromBookEntityToBook(bookRepository.create(bookEntity).get());

        return BookMapper.getInstance().fromBookToBookDto(bookCreated);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return null;

    }

    @Override
    public void delete(String isbn) {
        //https://code-with-me.global.jetbrains.com/0ztDW69ZHs478ZoB8HbHIg
    }

    @Override
    public Optional<BookDto> findBySynopsisEs(String synopsisEs) {
    return null;
    }

    @Override
    public Optional<BookDto> findByCover(String cover) {
        return Optional.empty();
    }

    @Override
    public List<BookDto> findByName(String name) {
        return bookRepository
                .findByName(name)
                .stream()
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto)
                .toList();
    }
}

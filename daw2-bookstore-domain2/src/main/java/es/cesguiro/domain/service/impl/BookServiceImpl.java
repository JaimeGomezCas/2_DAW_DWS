package es.cesguiro.domain.service.impl;

import es.cesguiro.domain.exception.BusinessException;
import es.cesguiro.domain.exception.ResourceNotFoundException;
import es.cesguiro.domain.mapper.BookMapper;
import es.cesguiro.domain.model.Book;
import es.cesguiro.domain.repository.entity.BookEntity;
import es.cesguiro.domain.service.dto.BookDto;
import es.cesguiro.domain.repository.BookRepository;
import es.cesguiro.domain.service.BookService;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public List<BookDto> getAll(int page, int size) {
        return bookRepository
                .findAll(page, size)
                .stream()
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto)
                .toList();
    }

    @Override
    @Transactional
    public BookDto getByIsbn(String isbn) {
        return bookRepository
                .findByIsbn(isbn)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book with isbn " + isbn + " not found"));
    }

    @Override
    @Transactional
    public Optional<BookDto> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto);
    }

    @Override
    @Transactional
    public BookDto create(BookDto bookDto) {
        if (bookRepository.findByIsbn(bookDto.isbn()).isEmpty()) {
            return bookRepository.create(
                            Optional.of(bookDto)
                                    .map(BookMapper.getInstance()::fromBookDtoToBook)
                                    .map(BookMapper.getInstance()::fromBookToBookEntity)
                                    .orElseThrow(() -> new BusinessException("No va"))
                    )
                    .stream()
                    .map(BookMapper.getInstance()::fromBookEntityToBook)
                    .map(BookMapper.getInstance()::fromBookToBookDto)
                    .findAny()
                    .orElseThrow(() -> new BusinessException("No va"));
        } else {
            throw new BusinessException("Ya existe el libro que quieres crear");
        }

    }

    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {

        if (bookRepository.findByIsbn(bookDto.isbn()).isEmpty()) {
            throw new ResourceNotFoundException("No existe el libro que buscas actualizar");
        } else {
            return bookRepository.save(
                            Optional.of(bookDto)
                                    .map(BookMapper.getInstance()::fromBookDtoToBook)
                                    .map(BookMapper.getInstance()::fromBookToBookEntity)
                                    .orElseThrow(() -> new BusinessException("No va"))
                    )
                    .stream()
                    .map(BookMapper.getInstance()::fromBookEntityToBook)
                    .map(BookMapper.getInstance()::fromBookToBookDto)
                    .findAny()
                    .orElseThrow(() -> new BusinessException("No va"));
        }

    }

    @Override
    @Transactional
    public void delete(String isbn) {
        //https://code-with-me.global.jetbrains.com/0ztDW69ZHs478ZoB8HbHIg
    }

    @Override
    @Transactional
    public Optional<BookDto> findBySynopsisEs(String synopsisEs) {
        return bookRepository.findBySynopsisEs(synopsisEs)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto);
    }

    public Optional<BookDto> findBySynopsisEn(String synopsisEn) {
        return bookRepository.findBySynopsisEs(synopsisEn)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto);
    }

    @Override
    @Transactional
    public Optional<BookDto> findByCover(String cover) {
        return bookRepository.findByIsbn(cover)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto);
    }

    @Override
    @Transactional
    public List<BookDto> findByName(String name) {
        return bookRepository.findByName(name)
                .stream()
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto)
                .toList();
    }
}

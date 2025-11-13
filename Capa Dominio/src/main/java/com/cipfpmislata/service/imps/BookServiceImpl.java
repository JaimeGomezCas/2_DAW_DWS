package com.cipfpmislata.service.imps;

import com.cipfpmislata.exception.BusinessException;
import com.cipfpmislata.exception.ResourceNotFoundException;
import com.cipfpmislata.mapper.BookMapper;
import com.cipfpmislata.model.Book;
import com.cipfpmislata.model.Page;
import com.cipfpmislata.repository.BookRepository;
import com.cipfpmislata.repository.entity.BookEntity;
import com.cipfpmislata.service.BookService;
import com.cipfpmislata.service.dto.BookDto;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<BookDto> getAll(int page, int size) {
        Page<BookEntity> bookEntityPage = bookRepository.getAll(page, size);

        List<BookDto> bookDtoList = bookEntityPage
                .data()
                .stream()
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto)
                .toList();
        return new Page<>(
                bookDtoList,
                bookEntityPage.pageIndex(),
                bookEntityPage.pageSize(),
                bookEntityPage.totalElements()
        );
    }

    @Override
    public Optional<BookDto> findByIsbn(String isbn) {
        return bookRepository
                .findByIsbn(isbn)
                .map(BookMapper.getInstance()::fromBookEntityToBook)
                .map(BookMapper.getInstance()::fromBookToBookDto);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if(bookRepository.findByIsbn(bookDto.isbn()).isPresent()){
            throw new BusinessException("the book already exists");
        }
        BookEntity newBookEntity = bookRepository.save(
                BookMapper.getInstance().fromBookToBookEntity(
                        BookMapper.getInstance().fromBookDtoToBook(bookDto)
                )
        );
        return BookMapper.getInstance().fromBookToBookDto(
                BookMapper.getInstance().fromBookEntityToBook(newBookEntity)
        );
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if(bookRepository.findByIsbn(bookDto.isbn()).isEmpty()){
            throw new BusinessException("the book doesnt exists");
        }
        BookEntity newBookEntity = bookRepository.save(
                BookMapper.getInstance().fromBookToBookEntity(
                        BookMapper.getInstance().fromBookDtoToBook(bookDto)
                )
        );
        return BookMapper.getInstance().fromBookToBookDto(
                BookMapper.getInstance().fromBookEntityToBook(newBookEntity)
        );
    }

    @Override
    public void deleteByIsbn(String isbn) {
        if(bookRepository.findByIsbn(isbn).isEmpty()){
            throw new BusinessException("the book doesnt exists");
        }
        bookRepository.deleteByIsbn(isbn);
    }
}

package com.cipfpmislata.service;

import com.cipfpmislata.model.Page;
import com.cipfpmislata.service.dto.BookDto;

import java.util.Optional;

public interface BookService {
    Page<BookDto> getAll(int page, int size);
    Optional<BookDto> findByIsbn(String isbn);
    BookDto create(BookDto bookDto);
    BookDto update(BookDto bookDto);
    void deleteByIsbn(String isbn);
}

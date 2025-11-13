package com.cipfpmislata.repository;

import com.cipfpmislata.model.Page;
import com.cipfpmislata.repository.entity.BookEntity;

import java.util.Optional;

public interface BookRepository {
    Page<BookEntity> getAll(int page, int size);
    Optional<BookEntity> findByIsbn(String isbn);
    Optional<BookEntity> findById(Long id);
    void deleteByIsbn(String isbn);
    void deleteById(Long id);
    BookEntity save(BookEntity bookEntity);
}

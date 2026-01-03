package com.cipfpmislata.persistence.repository;


import com.cipfpmislata.model.Page;
import com.cipfpmislata.repository.BookRepository;
import com.cipfpmislata.repository.entity.BookEntity;

import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public Page<BookEntity> getAll(int i, int i1) {
        return null;
    }

    @Override
    public Optional<BookEntity> findByIsbn(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<BookEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteByIsbn(String s) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public BookEntity save(BookEntity bookEntity) {
        return null;
    }
}

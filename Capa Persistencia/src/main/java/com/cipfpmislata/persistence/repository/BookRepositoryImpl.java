package com.cipfpmislata.persistence.repository;

import es.cesguiro.domain.repository.BookRepository;
import es.cesguiro.domain.repository.entity.BookEntity;

import java.util.List;
import java.util.Optional;


public class BookRepositoryImpl implements BookRepository{
    @Override
    public List<BookEntity> findAll(int page, int size) {
        return List.of();
    }

    @Override
    public Optional<BookEntity> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<BookEntity> findByName(String name) {
        return List.of();
    }

    @Override
    public Optional<BookEntity> create(BookEntity bookEntity) {
        return Optional.empty();
    }

    @Override
    public Optional<BookEntity> save(BookEntity bookEntity) {
        return Optional.empty();
    }

    @Override
    public Optional<BookEntity> findBySynopsisEs(String synopsisEs) {
        return Optional.empty();
    }

    @Override
    public Optional<BookEntity> findByCover(String cover) {
        return Optional.empty();
    }

    @Override
    public Optional<BookEntity> findBySynopsisEn(String synopsisEn) {
        return Optional.empty();
    }
}

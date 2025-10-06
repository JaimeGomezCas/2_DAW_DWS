package es.cesguiro.repository;

import es.cesguiro.repository.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<BookEntity> findAll(int page, int size);

    Optional<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findByName(String name);

    Optional<BookEntity> create(BookEntity bookEntity);

    Optional<BookEntity> update(BookEntity bookEntity);

    Optional<BookEntity> findBySynopsisEs(String synopsisEs);

    Optional<BookEntity> findByCover(String cover);

    Optional<BookEntity> findBySynopsisEn(String synopsisEn);
}

package com.cipfpmislata.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;

public class BookAuthorJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookJpaEntity book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorJpaEntity author;

    public BookAuthorJpaEntity(BookJpaEntity book, AuthorJpaEntity author) {
        this.book = book;
        this.author = author;
    }

    public BookAuthorJpaEntity() {
    }

    public BookJpaEntity getBook() {
        return book;
    }

    public void setBook(BookJpaEntity book) {
        this.book = book;
    }

    public AuthorJpaEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorJpaEntity author) {
        this.author = author;
    }
}

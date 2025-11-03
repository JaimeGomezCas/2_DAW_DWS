package com.cipfpmislata.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BookJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String isbn;
    @Column(name = "title_es")
    private String titleEs;
    @Column(name = "title_en")
    private String titleEn;
    @Column(name = "synopsis_es")
    private String synopsisEs;
    @Column(name = "synopsis_en")
    private String synopsisEn;
    @Column(name = "base_price")
    private BigDecimal basePrice;
    @Column(name = "discount_percentage")
    private double discountPercentage;
    private String cover;
    @Column(name = "publication_date")
    private String publicationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private PublisherJpaEntity publisher;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthorJpaEntity> bookAuthors;


    public BookJpaEntity(Long id, String isbn, String titleEs, String titleEn, String synopsisEs, String synopsisEn, BigDecimal basePrice, Double discountPercentage, String cover, String publicationDate, PublisherJpaEntity publisher, List<AuthorJpaEntity> authors) {
        this.id = id;
        this.isbn = isbn;
        this.titleEs = titleEs;
        this.titleEn = titleEn;
        this.synopsisEs = synopsisEs;
        this.synopsisEn = synopsisEn;
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
        this.cover = cover;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        setAuthors(authors);
    }
    public BookJpaEntity() {
    }
    private void setAuthors(List<AuthorJpaEntity> authors) {
        this.bookAuthors.clear();
        for(AuthorJpaEntity author: authors){
            BookAuthorJpaEntity bookAuthorJpaEntity = new BookAuthorJpaEntity(this, author);
            bookAuthors.add(bookAuthorJpaEntity);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitleEs() {
        return titleEs;
    }

    public void setTitleEs(String titleEs) {
        this.titleEs = titleEs;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getSynopsisEs() {
        return synopsisEs;
    }

    public void setSynopsisEs(String synopsisEs) {
        this.synopsisEs = synopsisEs;
    }

    public String getSynopsisEn() {
        return synopsisEn;
    }

    public void setSynopsisEn(String synopsisEn) {
        this.synopsisEn = synopsisEn;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public PublisherJpaEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherJpaEntity publisher) {
        this.publisher = publisher;
    }

    public List<BookAuthorJpaEntity> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthorJpaEntity> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
    public List<AuthorJpaEntity> getAuthors(){
        return bookAuthors.stream().map(BookAuthorJpaEntity:: getAuthor).collect(Collectors.toList());
    }
}

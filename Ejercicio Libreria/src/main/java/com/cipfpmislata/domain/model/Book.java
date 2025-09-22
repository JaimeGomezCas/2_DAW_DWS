package com.cipfpmislata.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Book {
    private String isbn;
    private String titleEs;
    private String titleEn;
    private String synopsisEs;
    private String synopsisEn;
    private BigDecimal basePrice;
    private double discountPercentage;
    private BigDecimal price;
    private String cover;
    private LocalDate publicationDate;
    private Publisher publisher;
    private List<Author> authors;

    public Book(String isbn, String titleEs, String titleEn, String synopsisEs, String synopsisEn, BigDecimal basePrice, double discountPercentage, BigDecimal price, String cover, LocalDate publicationDate, Publisher publisher, List<Author> authors) {
        this.isbn = isbn;
        this.titleEs = titleEs;
        this.titleEn = titleEn;
        this.synopsisEs = synopsisEs;
        this.synopsisEn = synopsisEn;
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
        this.price = price;
        this.cover = cover;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.authors = authors;
    }
    public BigDecimal calculateFinalPrice() {
        BigDecimal discount = basePrice
                .multiply(BigDecimal.valueOf(discountPercentage))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return basePrice.subtract(discount).setScale(2, RoundingMode.HALF_UP);
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(discountPercentage, book.discountPercentage) == 0 && Objects.equals(isbn, book.isbn) && Objects.equals(titleEs, book.titleEs) && Objects.equals(titleEn, book.titleEn) && Objects.equals(synopsisEs, book.synopsisEs) && Objects.equals(synopsisEn, book.synopsisEn) && Objects.equals(basePrice, book.basePrice) && Objects.equals(price, book.price) && Objects.equals(cover, book.cover) && Objects.equals(publicationDate, book.publicationDate) && Objects.equals(publisher, book.publisher) && Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, titleEs, titleEn, synopsisEs, synopsisEn, basePrice, discountPercentage, price, cover, publicationDate, publisher, authors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", titleEs='" + titleEs + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", synopsisEs='" + synopsisEs + '\'' +
                ", synopsisEn='" + synopsisEn + '\'' +
                ", basePrice=" + basePrice +
                ", discountPercentage=" + discountPercentage +
                ", price=" + price +
                ", cover='" + cover + '\'' +
                ", publicationDate=" + publicationDate +
                ", publisher=" + publisher +
                ", authors=" + authors +
                '}';
    }
}

package com.cipfpmislata.service.dto;

import jakarta.validation.constraints.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookDto (
        Long id,
        @NotNull
        @Pattern(regexp = "^97[89]-\\d{1,5}-\\d{1,7}-\\d{1,6}-\\d$", message = "tiene que tener un isbn valido")
        String isbn,
        @NotBlank
        String titleEs,
        @NotBlank
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        @Positive
        BigDecimal basePrice,
        @PositiveOrZero
        BigDecimal discountPercentage,
        @Positive
        BigDecimal finalPrice,
        String cover,
        LocalDate publicationDate,
        PublisherDto publisher,
        List<AuthorDto> authors
) {
    public BookDto(
            Long id,
            String isbn,
            String titleEs,
            String titleEn,
            String synopsisEs,
            String synopsisEn,
            BigDecimal basePrice,
            BigDecimal discountPercentage,
            BigDecimal finalPrice,
            String cover,
            LocalDate publicationDate,
            PublisherDto publisher,
            List<AuthorDto> authors
    ) {
        this.id = id;
        this.isbn = isbn;
        this.titleEs = titleEs;
        this.titleEn = titleEn;
        this.synopsisEs = synopsisEs;
        this.synopsisEn = synopsisEn;
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
        this.finalPrice = finalPrice;
        this.cover = cover;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.authors = (authors == null? List.of(): List.copyOf(authors));
    }
}

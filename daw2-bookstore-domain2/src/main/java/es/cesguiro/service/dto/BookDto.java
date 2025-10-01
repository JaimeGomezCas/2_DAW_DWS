package es.cesguiro.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookDto(
        @NotNull(message = "Isbn no puede ser null")
        @Pattern(regexp = "^(?:ISBN(?:-1[03])?:?\\s*)?(?=[-0-9X\\s]{10,17}$)(?:97[89][-\\s]?)?[0-9]{1,5}[-\\s]?[0-9]+[-\\s]?[0-9]+[-\\s]?[0-9X]$\n",
        message = "Isbn tiene un formato invalido")
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        @NotNull(message = "price cant be null")
        @Min(0)
        BigDecimal basePrice,
        @NotNull(message = "Discount cant be null")
        @Pattern(regexp = "^100(?:\\.0{1,2})?%$|^\\d{1,2}(?:\\.\\d{1,2})?%$\n",
        message = "Discount not valid")
        double discountPercentage,
        @NotNull(message = "price cant be null")
        @Min(0)
        BigDecimal price,
        @Pattern(regexp = "^[\\w,\\s-]+\\.(jpg|jpeg|png|gif|bmp|webp|tiff)$\n",
        message = "Cover doest have a valid file extension")
        String cover,
        LocalDate publicationDate,
        @NotNull(message = "Publisher cant be null")
        PublisherDto publisher,
        @NotNull(message = "Authors cant be null")
        List<AuthorDto> authors
) {
}

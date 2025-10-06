package es.cesguiro.domain.service.dto;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AuthorDto(
        @NotNull(message = "No puede ser nulo")
        String name,
        String nationality,
        String biographyEs,
        String biographyEn,
        int birthYear,
        Integer deathYear,
        @NotNull(message = "No puede ser nulo")
        @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",
                message = "Slug inválido, debe ser URL-friendly (minúsculas, números y guiones)")
        String slug
) {

}

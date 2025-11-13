package com.cipfpmislata.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PublisherDto(
        Long id,
        @NotBlank
        String name,
        @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$")
        String slug
) {
}

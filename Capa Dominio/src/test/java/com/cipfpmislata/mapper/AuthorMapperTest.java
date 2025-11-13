package com.cipfpmislata.mapper;

import com.cipfpmislata.model.Author;
import com.cipfpmislata.repository.entity.AuthorEntity;
import com.cipfpmislata.service.dto.AuthorDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class AuthorMapperTest {
    //Me da palo hacerlo de otra forma, los declaro aquí para usarlos en todos los test
    Author author = new Author(
            1L,
            "Cervantes",
            "Español",
            "El puto amo",
            "The goat",
            1547,
            1616,
            "cervantes"
    );
    AuthorDto authorDto = new AuthorDto(
            1L,
            "Cervantes",
            "Español",
            "El puto amo",
            "The goat",
            1547,
            1616,
            "cervantes"
    );
    AuthorEntity authorEntity = new AuthorEntity(
            1L,
            "Cervantes",
            "Español",
            "El puto amo",
            "The goat",
            1547,
            1616,
            "cervantes"
    );
    AuthorMapper authorMapper = AuthorMapper.getInstance();
    @Test
    @DisplayName("Map Author to AuthorEntity should return a correct AuthorEntity")
    void fromAuthorToAuthorEntity() {
        AuthorEntity mappedAuthorEntity = authorMapper.fromAuthorToAuthorEntity(author);
        assertAll(
                ()-> assertEquals(mappedAuthorEntity.id(), author.getId()),
                ()-> assertEquals(mappedAuthorEntity.name(), author.getName()),
                ()-> assertEquals(mappedAuthorEntity.nationality(), author.getNationality()),
                ()-> assertEquals(mappedAuthorEntity.biographyEs(), author.getBiographyEs()),
                ()-> assertEquals(mappedAuthorEntity.biographyEn(), author.getBiographyEn()),
                ()-> assertEquals(mappedAuthorEntity.birthYear(), author.getBirthYear()),
                ()-> assertEquals(mappedAuthorEntity.deathYear(), author.getDeathYear()),
                ()-> assertEquals(mappedAuthorEntity.slug(), author.getSlug())
        );
    }

    @Test
    @DisplayName("Map AuthorEntity to Author should return a correct Author")
    void fromAuthorEntityToAuthor() {
        Author mappedAuthor = authorMapper.fromAuthorEntityToAuthor(authorEntity);
        assertAll(
                ()-> assertEquals(mappedAuthor.getId(),  authorEntity.id()),
                ()-> assertEquals(mappedAuthor.getName(), authorEntity.name()),
                ()-> assertEquals(mappedAuthor.getNationality(), authorEntity.nationality()),
                ()-> assertEquals(mappedAuthor.getBiographyEs(), authorEntity.biographyEs()),
                ()-> assertEquals(mappedAuthor.getBiographyEn(), authorEntity.biographyEn()),
                ()-> assertEquals(mappedAuthor.getBirthYear(), authorEntity.birthYear()),
                ()-> assertEquals(mappedAuthor.getDeathYear(), authorEntity.deathYear()),
                ()-> assertEquals(mappedAuthor.getSlug(), authorEntity.slug())
        );
    }

    @Test
    @DisplayName("Map AuthorDto to Author should return a correct Author")
    void fromAuthorDtoToAuthor() {
        Author mappedAuthor = authorMapper.fromAuthorDtoToAuthor(authorDto);
        assertAll(
                ()-> assertEquals(mappedAuthor.getId(),  authorDto.id()),
                ()-> assertEquals(mappedAuthor.getName(), authorDto.name()),
                ()-> assertEquals(mappedAuthor.getNationality(), authorDto.nationality()),
                ()-> assertEquals(mappedAuthor.getBiographyEs(), authorDto.biographyEs()),
                ()-> assertEquals(mappedAuthor.getBiographyEn(), authorDto.biographyEn()),
                ()-> assertEquals(mappedAuthor.getBirthYear(), authorDto.birthYear()),
                ()-> assertEquals(mappedAuthor.getDeathYear(), authorDto.deathYear()),
                ()-> assertEquals(mappedAuthor.getSlug(), authorDto.slug())
        );
    }

    @Test
    @DisplayName("Map Author to AuthorDto should return a correct AuthorDto")
    void fromAuthorToAuthorDto() {
        AuthorDto mappedAuthorDto = authorMapper.fromAuthorToAuthorDto(author);
        assertAll(
                ()-> assertEquals(mappedAuthorDto.id(), author.getId()),
                ()-> assertEquals(mappedAuthorDto.name(), author.getName()),
                ()-> assertEquals(mappedAuthorDto.nationality(), author.getNationality()),
                ()-> assertEquals(mappedAuthorDto.biographyEs(), author.getBiographyEs()),
                ()-> assertEquals(mappedAuthorDto.biographyEn(), author.getBiographyEn()),
                ()-> assertEquals(mappedAuthorDto.birthYear(), author.getBirthYear()),
                ()-> assertEquals(mappedAuthorDto.deathYear(), author.getDeathYear()),
                ()-> assertEquals(mappedAuthorDto.slug(), author.getSlug())
        );
    }

    @Test
    @DisplayName("Mapping a null author returns null")
    void allMapperMethodsReturnNull_WhenGivenANullAuthor() {
        assertAll(
                () -> assertNull(AuthorMapper.getInstance().fromAuthorDtoToAuthor(null)),
                () -> assertNull(AuthorMapper.getInstance().fromAuthorEntityToAuthor(null)),
                () -> assertNull(AuthorMapper.getInstance().fromAuthorToAuthorDto(null)),
                () -> assertNull(AuthorMapper.getInstance().fromAuthorToAuthorEntity(null))
        );
    }
}
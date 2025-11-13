package com.cipfpmislata.mapper;

import com.cipfpmislata.model.Author;
import com.cipfpmislata.repository.entity.AuthorEntity;
import com.cipfpmislata.service.dto.AuthorDto;

public class AuthorMapper {
    private static AuthorMapper INSTANCE;
    private AuthorMapper(){}

    public static AuthorMapper getInstance(){
        if(INSTANCE == null){
            INSTANCE = new AuthorMapper();
        }
        return INSTANCE;
    }
    public AuthorEntity fromAuthorToAuthorEntity(Author author){
        if (author == null){
            return null;
        }
        return new AuthorEntity(
                author.getId(),
                author.getName(),
                author.getNationality(),
                author.getBiographyEs(),
                author.getBiographyEn(),
                author.getBirthYear(),
                author.getDeathYear(),
                author.getSlug()
        );

    }
    public Author fromAuthorEntityToAuthor(AuthorEntity authorEntity){
        if(authorEntity == null){
            return null;
        }
        return new Author(
                authorEntity.id(),
                authorEntity.name(),
                authorEntity.nationality(),
                authorEntity.biographyEs(),
                authorEntity.biographyEn(),
                authorEntity.birthYear(),
                authorEntity.deathYear(),
                authorEntity.slug()
        );
    }
    public Author fromAuthorDtoToAuthor(AuthorDto authorDto){
        if (authorDto == null){
            return null;
        }
        return new Author(
                authorDto.id(),
                authorDto.name(),
                authorDto.nationality(),
                authorDto.biographyEs(),
                authorDto.biographyEn(),
                authorDto.birthYear(),
                authorDto.deathYear(),
                authorDto.slug()
        );
    }
    public AuthorDto fromAuthorToAuthorDto(Author author){
        if (author == null){
            return null;
        }
        return new AuthorDto(
                author.getId(),
                author.getName(),
                author.getNationality(),
                author.getBiographyEs(),
                author.getBiographyEn(),
                author.getBirthYear(),
                author.getDeathYear(),
                author.getSlug()
        );

    }

}

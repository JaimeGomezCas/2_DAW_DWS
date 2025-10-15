package es.cesguiro.domain.service.impl;

import es.cesguiro.domain.mapper.AuthorMapper;
import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.repository.AuthorRepository;
import es.cesguiro.domain.repository.entity.AuthorEntity;
import es.cesguiro.domain.service.dto.AuthorDto;
import es.cesguiro.domain.service.AuthorService;
import jakarta.transaction.Transactional;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public List<AuthorDto> getAll() {
        return List.of();
    }

    @Override
    @Transactional
    public AuthorDto getBySlug(String slug) {
        return null;
    }

    @Override
    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        Author author = AuthorMapper.getInstance().fromAuthorDtoToAuthor(authorDto);
        AuthorEntity authorEntity = AuthorMapper.getInstance().fromAuthorToAuthorEntity(author);
        AuthorEntity newAuthorEntity = authorRepository.create(authorEntity);
        Author newAuthor = AuthorMapper.getInstance().fromAuthorEntityToAuthor(newAuthorEntity);
        return AuthorMapper.getInstance().fromAuthorToAuthorDto(newAuthor);
    }

    @Override
    @Transactional
    public AuthorDto update(AuthorDto authorDto) {
        return null;
    }

    @Override
    @Transactional
    public int delete(String slug) {
        return 0;
    }
}

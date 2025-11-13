package com.cipfpmislata.service.imps;

import com.cipfpmislata.exception.BusinessException;
import com.cipfpmislata.exception.ResourceNotFoundException;
import com.cipfpmislata.mapper.AuthorMapper;
import com.cipfpmislata.model.Page;
import com.cipfpmislata.repository.AuthorRepository;
import com.cipfpmislata.repository.BookRepository;
import com.cipfpmislata.repository.entity.AuthorEntity;
import com.cipfpmislata.service.AuthorService;
import com.cipfpmislata.service.dto.AuthorDto;
import com.cipfpmislata.service.dto.BookDto;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<AuthorDto> getAll(int page, int size) {
        Page<AuthorEntity> authorEntityPage = authorRepository
                .getAll(page, size);

        List<AuthorDto> authorDtoList = authorEntityPage
                .data()
                .stream()
                .map(AuthorMapper.getInstance()::fromAuthorEntityToAuthor)
                .map(AuthorMapper.getInstance()::fromAuthorToAuthorDto)
                .toList();
        return new Page<>(
            authorDtoList,
            authorEntityPage.pageIndex(),
            authorEntityPage.pageSize(),
            authorEntityPage.totalElements()
        );
    }

    @Override
    public AuthorDto getBySlug(String slug) {
        return authorRepository
                .findBySlug(slug)
                .map(AuthorMapper.getInstance()::fromAuthorEntityToAuthor)
                .map(AuthorMapper.getInstance()::fromAuthorToAuthorDto)
                .orElseThrow(()-> new ResourceNotFoundException("Author with slug: " + slug + "not found."));
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        if(authorRepository.findBySlug(authorDto.slug()).isPresent()){
            throw new BusinessException("Author with slug " + authorDto.slug() + "already exists");
        }
        AuthorEntity newAuthorEntity = authorRepository.save(
                AuthorMapper.getInstance().fromAuthorToAuthorEntity(AuthorMapper.getInstance().fromAuthorDtoToAuthor(authorDto))
        );
        return AuthorMapper.getInstance().fromAuthorToAuthorDto(
                AuthorMapper.getInstance().fromAuthorEntityToAuthor(newAuthorEntity)
        );
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        if(authorRepository.findBySlug(authorDto.slug()).isEmpty()){
            throw new BusinessException("Author with slug " + authorDto.slug() + "doesnt exists");
        }
        AuthorEntity newAuthorEntity = authorRepository.save(
                AuthorMapper.getInstance().fromAuthorToAuthorEntity(AuthorMapper.getInstance().fromAuthorDtoToAuthor(authorDto))
        );
        return AuthorMapper.getInstance().fromAuthorToAuthorDto(
                AuthorMapper.getInstance().fromAuthorEntityToAuthor(newAuthorEntity)
        );

    }

    @Override
    public void delete(String slug) {
        if(authorRepository.findBySlug(slug).isEmpty()){
            throw new BusinessException("Author with slug " + slug + "doesnt exists");
        }
        authorRepository.deleteBySlug(slug);
    }
}

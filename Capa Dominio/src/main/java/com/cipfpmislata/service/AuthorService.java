package com.cipfpmislata.service;

import com.cipfpmislata.model.Page;
import com.cipfpmislata.service.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    Page<AuthorDto> getAll(int page, int size);
    AuthorDto getBySlug(String slug);
    AuthorDto create(AuthorDto authorDto);
    AuthorDto update(AuthorDto authorDto);
    void delete(String slug);
}

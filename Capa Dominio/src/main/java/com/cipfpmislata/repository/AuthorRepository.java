package com.cipfpmislata.repository;


import com.cipfpmislata.model.Page;
import com.cipfpmislata.repository.entity.AuthorEntity;


import java.util.Optional;

public interface AuthorRepository {
    Page<AuthorEntity> getAll(int page, int size);
    Optional<AuthorEntity> findBySlug(String slug);
    Optional<AuthorEntity> findById(Long id);
    void deleteBySlug(String slug);
    void deleteById(Long id);
    AuthorEntity save(AuthorEntity authorEntity);
}

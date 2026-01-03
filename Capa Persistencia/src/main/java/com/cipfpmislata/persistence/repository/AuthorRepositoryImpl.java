package com.cipfpmislata.persistence.repository;

import com.cipfpmislata.model.Page;
import com.cipfpmislata.repository.AuthorRepository;
import com.cipfpmislata.repository.entity.AuthorEntity;

import java.util.Optional;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public Page<AuthorEntity> getAll(int i, int i1) {
        return null;
    }

    @Override
    public Optional<AuthorEntity> findBySlug(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<AuthorEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteBySlug(String s) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return null;
    }
}

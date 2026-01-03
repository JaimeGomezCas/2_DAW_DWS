package com.cipfpmislata.persistence.repository;



import com.cipfpmislata.model.Page;
import com.cipfpmislata.repository.PublisherRepository;
import com.cipfpmislata.repository.entity.PublisherEntity;


import java.util.Optional;

public class PublisherRepositoryImpl implements PublisherRepository {


    @Override
    public Page<PublisherEntity> getAll(int i, int i1) {
        return null;
    }

    @Override
    public Optional<PublisherEntity> findBySlug(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<PublisherEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteBySlug(String s) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public PublisherEntity save(PublisherEntity publisherEntity) {
        return null;
    }
}

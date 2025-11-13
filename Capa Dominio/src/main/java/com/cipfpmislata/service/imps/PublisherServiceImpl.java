package com.cipfpmislata.service.imps;

import com.cipfpmislata.exception.BusinessException;
import com.cipfpmislata.exception.ResourceNotFoundException;
import com.cipfpmislata.mapper.PublisherMapper;
import com.cipfpmislata.model.Page;
import com.cipfpmislata.model.Publisher;
import com.cipfpmislata.repository.PublisherRepository;
import com.cipfpmislata.repository.entity.PublisherEntity;
import com.cipfpmislata.service.PublisherService;
import com.cipfpmislata.service.dto.PublisherDto;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Page<PublisherDto> getAll(int page, int size) {
        Page<PublisherEntity> publisherEntityPage=  publisherRepository.getAll(page, size);

        List<PublisherDto> publisherDtoList = publisherEntityPage
                .data()
                .stream()
                .map(PublisherMapper.getInstance()::fromPublisherEntityToPublisher)
                .map(PublisherMapper.getInstance()::fromPublisherToPublisherDto)
                .toList();

        return new Page<>(
                publisherDtoList,
                publisherEntityPage.pageIndex(),
                publisherEntityPage.pageSize(),
                publisherEntityPage.totalElements()
        );
    }

    @Override
    public PublisherDto getBySlug(String slug) {
        return publisherRepository
                .findBySlug(slug)
                .map(PublisherMapper.getInstance()::fromPublisherEntityToPublisher)
                .map(PublisherMapper.getInstance()::fromPublisherToPublisherDto)
                .orElseThrow(()-> new ResourceNotFoundException("the publisher with slug " + slug + " doesnt exist"));
    }

    @Override
    public PublisherDto create(PublisherDto publisherDto) {
        if(publisherRepository.findBySlug(publisherDto.slug()).isPresent()){
            throw new BusinessException("Publisher already exists");
        }
        PublisherEntity publisherEntity = publisherRepository
                .save(
                        PublisherMapper.getInstance().fromPublisherToPublisherEntity(
                                PublisherMapper.getInstance().fromPublisherDtoToPublisher(publisherDto)
                        )
                );
        return PublisherMapper.getInstance().fromPublisherToPublisherDto(
                PublisherMapper.getInstance().fromPublisherEntityToPublisher(publisherEntity)
        );

    }

    @Override
    public PublisherDto update(PublisherDto publisherDto) {
        if(publisherRepository.findBySlug(publisherDto.slug()).isPresent()){
            throw new BusinessException("Publisher already exists");
        }
        PublisherEntity publisherEntity = publisherRepository
                .save(
                        PublisherMapper.getInstance().fromPublisherToPublisherEntity(
                                PublisherMapper.getInstance().fromPublisherDtoToPublisher(publisherDto)
                        )
                );
        return PublisherMapper.getInstance().fromPublisherToPublisherDto(
                PublisherMapper.getInstance().fromPublisherEntityToPublisher(publisherEntity)
        );
    }

    @Override
    public void delete(String slug) {
        if(publisherRepository.findBySlug(slug).isEmpty()){
            throw new BusinessException("Publisher already exists");
        }
        publisherRepository.deleteBySlug(slug);
    }
}

package es.cesguiro.domain.service.impl;

import es.cesguiro.domain.exception.ResourceNotFoundException;
import es.cesguiro.domain.mapper.PublisherMapper;
import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.repository.entity.PublisherEntity;
import es.cesguiro.domain.service.dto.PublisherDto;
import es.cesguiro.domain.service.PublisherService;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }



    @Override
    public List<PublisherDto> getAll(int page, int size) {
        return publisherRepository
                .findAll(page, size)
                .stream()
                .map(PublisherMapper.getInstance()::fromPublisherEntityToPublisher)
                .map(PublisherMapper.getInstance()::fromPublisherToPublisherDto)
                .toList();
    }

    @Override
    public PublisherDto getBySlug(String slug) {
        PublisherMapper mapper = PublisherMapper.getInstance();
        return publisherRepository.findBySlug(slug)
                .map(mapper::fromPublisherEntityToPublisher)
                .map(mapper::fromPublisherToPublisherDto)
                .orElseThrow(()->new ResourceNotFoundException("No existe el publisher ese"));

    }

    @Override
    public PublisherDto create(PublisherDto publisherDto) {
        Publisher publisher = PublisherMapper.getInstance().fromPublisherDtoToPublisher(publisherDto);
        PublisherEntity publisherEntity = PublisherMapper.getInstance().fromPublisherToPublisherEntity(publisher);
        Publisher publisherCreated = PublisherMapper.getInstance().fromPublisherEntityToPublisher(publisherRepository.create(publisherEntity).get());

        return PublisherMapper.getInstance().fromPublisherToPublisherDto(publisherCreated);
    }

    @Override
    public PublisherDto update(PublisherDto publisherDto) {
        return null;
    }

    @Override
    public PublisherDto delete(String slug) {
        return null;
    }


}

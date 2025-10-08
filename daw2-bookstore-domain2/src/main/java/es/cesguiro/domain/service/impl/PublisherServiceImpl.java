package es.cesguiro.domain.service.impl;

import es.cesguiro.domain.exception.BusinessException;
import es.cesguiro.domain.exception.ResourceNotFoundException;
import es.cesguiro.domain.mapper.BookMapper;
import es.cesguiro.domain.mapper.PublisherMapper;
import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.domain.model.Publisher;
import es.cesguiro.domain.repository.entity.PublisherEntity;
import es.cesguiro.domain.service.dto.PublisherDto;
import es.cesguiro.domain.service.PublisherService;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new ResourceNotFoundException("No existe el publisher ese"));

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
        PublisherEntity existingPublisher = publisherRepository.findById(publisherDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher with id " + publisherDto.id() + " not found"));
        PublisherEntity updatedPublisher = publisherRepository.save(
                PublisherMapper.getInstance().fromPublisherToPublisherEntity(
                        PublisherMapper.getInstance().fromPublisherDtoToPublisher(publisherDto)
                )
        ).get();
        return PublisherMapper.getInstance().fromPublisherToPublisherDto(
                PublisherMapper.getInstance().fromPublisherEntityToPublisher(updatedPublisher)
        );
    }

    /*@Override
    public PublisherDto update(PublisherDto publisherDto) {
        if(publisherRepository.findById(publisherDto.id()).isEmpty()) {
            throw new ResourceNotFoundException("Publisher con id: "+publisherDto.id()+" no existe");
        } else {
            return publisherRepository.save(
                    Optional.of(publisherDto)
                            .map(PublisherMapper.getInstance()::fromPublisherDtoToPublisher)
                            .map(PublisherMapper.getInstance()::fromPublisherToPublisherEntity)
                            .orElseThrow(() -> new BusinessException("No se pudo mapear el publisher en update"))
                    )
                    .stream()
                    .map(PublisherMapper.getInstance()::fromPublisherEntityToPublisher)
                    .map(PublisherMapper.getInstance()::fromPublisherToPublisherDto)
                    .findAny()
                    .orElseThrow(() -> new BusinessException("No se pudo devolver el publisher después del update"));
        }
    }*/

    @Override
    public PublisherDto delete(String slug) {
        return null;
    }


}

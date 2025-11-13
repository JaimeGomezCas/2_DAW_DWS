package com.cipfpmislata.service;

import com.cipfpmislata.model.Page;
import com.cipfpmislata.service.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    Page<PublisherDto> getAll(int page, int size);
    PublisherDto getBySlug(String slug);
    PublisherDto create(PublisherDto publisherDto);
    PublisherDto update(PublisherDto publisherDto);
    void delete(String slug);
}

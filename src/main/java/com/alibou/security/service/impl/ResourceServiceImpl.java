package com.alibou.security.service.impl;

import com.alibou.security.entity.Resource;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.image.ImageService;
import com.alibou.security.payload.ResourceDto;
import com.alibou.security.repository.ResourceRepository;
import com.alibou.security.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ImageService imageService;
    private final ResourceRepository resourceRepository;

    @Override
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource findById(Integer resourceId) {
        return resourceRepository.findById(resourceId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Resource create(ResourceDto resourceDto) throws IOException {
        String link = imageService.saveImage(resourceDto.getMultipartFile());
        Resource resource = Resource.builder()
                .fileLink(link)
                .description_uz(resourceDto.getDescription_uz())
                .description_en(resourceDto.getDescription_en())
                .description_ru(resourceDto.getDescription_ru())
                .build();
        return resourceRepository.save(resource);
    }

    @Override
    public Resource update(Integer resourceId, ResourceDto resourceDto) throws IOException {
        Resource existingResource = resourceRepository.findById(resourceId).orElseThrow(NotFoundException::new);

        if (resourceDto.getMultipartFile() != null) {
            String link = imageService.saveImage(resourceDto.getMultipartFile());
            existingResource.setFileLink(link);
        }
        updateIfNotNull(resourceDto.getDescription_uz(), existingResource::setDescription_uz);
        updateIfNotNull(resourceDto.getDescription_en(), existingResource::setDescription_en);
        updateIfNotNull(resourceDto.getDescription_ru(), existingResource::setDescription_ru);

        return resourceRepository.save(existingResource);
    }

    @Override
    public void delete(Integer resourceId) throws IOException {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(NotFoundException::new);
        imageService.deleteImage(resource.getFileLink());
        resourceRepository.deleteById(resourceId);
    }

    private <T> void updateIfNotNull(T value, java.util.function.Consumer<T> updater) {
        if (value != null) {
            updater.accept(value);
        }
    }
}

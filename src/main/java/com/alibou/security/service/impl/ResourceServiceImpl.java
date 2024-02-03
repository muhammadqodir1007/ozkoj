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

    private ImageService imageService;
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource findById(Integer id) {
        return resourceRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Resource create(ResourceDto resourceDto) throws IOException {
        String link = imageService.saveImage(resourceDto.getMultipartFile());
        Resource build = Resource.builder().fileLink(link).description_uz(resourceDto.getDescription_uz()).description_en(resourceDto.getDescription_en()).description_ru(resourceDto.getDescription_ru()).build();
        return resourceRepository.save(build);
    }

    @Override
    public Resource update(Integer resourceId, ResourceDto resourceDto) throws IOException {
        Resource existingResource = resourceRepository.findById(resourceId).orElseThrow(NotFoundException::new);

        if (resourceDto.getMultipartFile() != null) {
            String s = imageService.saveImage(resourceDto.getMultipartFile());
            existingResource.setFileLink(s);
        }

        if (resourceDto.getDescription_uz() != null) {
            existingResource.setDescription_uz(resourceDto.getDescription_uz());
        }
        if (resourceDto.getDescription_en() != null) {
            existingResource.setDescription_en(resourceDto.getDescription_en());
        }
        if (resourceDto.getDescription_ru() != null) {
            existingResource.setDescription_ru(resourceDto.getDescription_ru());
        }


        return resourceRepository.save(existingResource);
    }


    @Override
    public void delete(Integer id) throws IOException {
        Resource resource = resourceRepository.findById(id).orElseThrow(NotFoundException::new);
        imageService.deleteImage(resource.getFileLink());
        resourceRepository.deleteById(id);
    }
}

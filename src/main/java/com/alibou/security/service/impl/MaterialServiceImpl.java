package com.alibou.security.service.impl;

import com.alibou.security.entity.Material;
import com.alibou.security.repository.MaterialRepository;
import com.alibou.security.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material findById(Integer materialId) {
        return materialRepository.findById(materialId).orElseThrow(NullPointerException::new);
    }

    @Override
    public Material create(Material entity) throws IOException {
        entity.setCreatedDate(LocalDateTime.now());
        return materialRepository.save(entity);
    }

    @Override
    public Material update(Integer materialId, Material entity) throws IOException {
        Material material = materialRepository.findById(materialId).orElseThrow(NullPointerException::new);

        if (entity.getDescription_ru() != null) {
            material.setDescription_ru(entity.getDescription_ru());
        }
        if (entity.getDescription_en() != null) {
            material.setDescription_en(entity.getDescription_en());
        }
        if (entity.getLink() != null) {
            material.setLink(entity.getLink());
        }
        if (entity.getTitle_ru() != null) {
            material.setTitle_ru(entity.getTitle_ru());
        }
        if (entity.getTitle_en() != null) {
            material.setTitle_en(entity.getTitle_en());
        }

        return materialRepository.save(material);
    }

    @Override
    public void delete(Integer materialId) {
        materialRepository.deleteById(materialId);
    }
}

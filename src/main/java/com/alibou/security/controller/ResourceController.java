package com.alibou.security.controller;

import com.alibou.security.entity.Resource;
import com.alibou.security.payload.ResourceDto;
import com.alibou.security.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
@AllArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public List<Resource> getAllResources() {
        return resourceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Integer id) {
        Resource resource = resourceService.findById(id);
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<Resource> createResource(
            @RequestParam("description_uz") String descriptionUz,
            @RequestParam("description_en") String descriptionEn,
            @RequestParam("description_ru") String descriptionRu,
            @RequestParam("file") MultipartFile multipartFile) throws IOException {

        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setDescription_uz(descriptionUz);
        resourceDto.setDescription_en(descriptionEn);
        resourceDto.setDescription_ru(descriptionRu);
        resourceDto.setMultipartFile(multipartFile);

        Resource createdResource = resourceService.create(resourceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(
            @PathVariable Integer id,
            @RequestParam(value = "description_uz", required = false) String descriptionUz,
            @RequestParam(value = "description_en", required = false) String descriptionEn,
            @RequestParam(value = "description_ru", required = false) String descriptionRu,
            @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {

        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setDescription_uz(descriptionUz);
        resourceDto.setDescription_en(descriptionEn);
        resourceDto.setDescription_ru(descriptionRu);
        resourceDto.setMultipartFile(multipartFile);

        Resource updatedResource = resourceService.update(id, resourceDto);
        return ResponseEntity.ok(updatedResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Integer id) {
        resourceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

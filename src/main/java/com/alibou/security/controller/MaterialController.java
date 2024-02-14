package com.alibou.security.controller;

import com.alibou.security.entity.Material;
import com.alibou.security.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;


    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materials = materialService.findAll();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Integer id) {
        Material material = materialService.findById(id);
        return new ResponseEntity<>(material, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) throws IOException {
        Material createdMaterial = materialService.create(material);
        return new ResponseEntity<>(createdMaterial, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable Integer id,
                                                   @RequestBody Material material) throws IOException {
        Material updatedMaterial = materialService.update(id, material);
        return new ResponseEntity<>(updatedMaterial, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer id) {
        materialService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

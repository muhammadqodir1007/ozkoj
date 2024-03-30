package uz.fazo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.payload.MaterialDto;
import uz.fazo.service.MaterialService;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialDto>> getAllMaterials() {
        List<MaterialDto> materials = materialService.getAll();
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<MaterialDto>> getAllMaterialsByUserId(@PathVariable int id) {
        List<MaterialDto> materials = materialService.getAllByUserId(id);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> getMaterialById(@PathVariable long id) {
        MaterialDto material = materialService.getById(id);
        return ResponseEntity.ok(material);
    }

    @PostMapping
    public ResponseEntity<MaterialDto> createMaterial(@RequestBody MaterialDto materialDto) {
        MaterialDto createdMaterial = materialService.create(materialDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaterial);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<MaterialDto> updateMaterial(@PathVariable long id, @RequestBody MaterialDto materialDto) {
        MaterialDto updatedMaterial = materialService.update(id, materialDto);
        return ResponseEntity.ok(updatedMaterial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable long id) {
        materialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

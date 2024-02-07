package com.alibou.security.controller;

import com.alibou.security.entity.Partners;
import com.alibou.security.payload.PartnersDto;
import com.alibou.security.service.PartnersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/partners")
public class PartnersController {

    private final PartnersService partnersService;

    @GetMapping
    public ResponseEntity<List<Partners>> getAllPartners() {
        List<Partners> partnersList = partnersService.findAll();
        return ResponseEntity.ok(partnersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partners> getPartnersById(@PathVariable Integer id) {
        Partners partners = partnersService.findById(id);
        return ResponseEntity.ok(partners);
    }

    @PostMapping
    public ResponseEntity<Partners> createPartners(
            @RequestParam String title_uz,
            @RequestParam String title_ru,
            @RequestParam String title_en,
            @RequestParam String url,
            @RequestParam MultipartFile file
    ) throws IOException {
        PartnersDto partnersDto = new PartnersDto();
        partnersDto.setTitle_uz(title_uz);
        partnersDto.setTitle_ru(title_ru);
        partnersDto.setTitle_en(title_en);
        partnersDto.setUrl(url);
        partnersDto.setFile(file);

        Partners createdPartners = partnersService.create(partnersDto);
        return new ResponseEntity<>(createdPartners, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Partners> updatePartners(
            @PathVariable Integer id,
            @RequestParam(value = "title_uz", required = false) String title_uz,
            @RequestParam(value = "title_ru", required = false) String title_ru,
            @RequestParam(value = "title_en", required = false) String title_en,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        PartnersDto partnersDto = new PartnersDto();
        partnersDto.setTitle_uz(title_uz);
        partnersDto.setTitle_ru(title_ru);
        partnersDto.setTitle_en(title_en);
        partnersDto.setUrl(url);
        partnersDto.setFile(file);

        Partners updatedPartners = partnersService.update(id, partnersDto);
        return ResponseEntity.ok(updatedPartners);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartners(@PathVariable Integer id) throws IOException {
        partnersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

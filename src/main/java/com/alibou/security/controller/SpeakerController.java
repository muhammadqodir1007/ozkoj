package com.alibou.security.controller;

import com.alibou.security.entity.Speakers;
import com.alibou.security.payload.SpeakerDto;
import com.alibou.security.service.SpeakerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/speakers")
@AllArgsConstructor
public class SpeakerController {
    private final SpeakerService speakerService;

    @GetMapping
    public ResponseEntity<List<Speakers>> getAllSpeakers() {
        List<Speakers> speakersList = speakerService.findAll();
        return ResponseEntity.ok(speakersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Speakers> getSpeakerById(@PathVariable Integer id) {
        Speakers speaker = speakerService.findById(id);
        return ResponseEntity.ok(speaker);
    }

    @PostMapping
    public ResponseEntity<Speakers> createSpeaker(
            @RequestParam("fullName") String fullName,
            @RequestParam("description_uz") String descriptionUz,
            @RequestParam("description_ru") String descriptionRu,
            @RequestParam("description_en") String descriptionEn,
            @RequestParam("file") MultipartFile file) throws IOException {
        SpeakerDto speakerDto = new SpeakerDto();
        speakerDto.setFullName(fullName);
        speakerDto.setDescription_uz(descriptionUz);
        speakerDto.setDescription_ru(descriptionRu);
        speakerDto.setDescription_en(descriptionEn);
        speakerDto.setFile(file);

        Speakers createdSpeaker = speakerService.create(speakerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpeaker);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Speakers> updateSpeaker(
            @PathVariable Integer id,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "description_uz", required = false) String descriptionUz,
            @RequestParam(value = "description_ru", required = false) String descriptionRu,
            @RequestParam(value = "description_en", required = false) String descriptionEn,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        SpeakerDto speakerDto = new SpeakerDto();
        speakerDto.setFullName(fullName);
        speakerDto.setFile(file);
        speakerDto.setDescription_uz(descriptionUz);
        speakerDto.setDescription_ru(descriptionRu);
        speakerDto.setDescription_en(descriptionEn);

        Speakers updatedSpeaker = speakerService.update(id, speakerDto);
        return ResponseEntity.ok(updatedSpeaker);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeaker(@PathVariable Integer id) throws IOException {
        speakerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

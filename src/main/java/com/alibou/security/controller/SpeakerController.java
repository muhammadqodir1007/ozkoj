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
        return new ResponseEntity<>(speakersList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Speakers> getSpeakerById(@PathVariable Integer id) {
        Speakers speaker = speakerService.findById(id);
        return new ResponseEntity<>(speaker, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Speakers> createSpeaker(@RequestParam("fullName") String fullName,
                                                  @RequestParam("description_uz") String description_uz,
                                                  @RequestParam("description_ru") String description_ru,
                                                  @RequestParam("description_en") String description_en,
                                                  @RequestParam("file") MultipartFile file) throws IOException {
        SpeakerDto speakerDto = new SpeakerDto();
        speakerDto.setFullName(fullName);
        speakerDto.setDescription_en(description_en);
        speakerDto.setDescription_ru(description_ru);
        speakerDto.setDescription_uz(description_uz);
        speakerDto.setFile(file);

        Speakers createdSpeaker = speakerService.create(speakerDto);
        return new ResponseEntity<>(createdSpeaker, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Speakers> updateSpeaker(@PathVariable Integer id,
                                                  @RequestParam(value = "fullName",required = false) String fullName,
                                                  @RequestParam(value = "description_uz", required = false) String description_uz,
                                                  @RequestParam(value = "description_ru", required = false) String description_ru,
                                                  @RequestParam(value = "description_en", required = false) String description_en,
                                                  @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        SpeakerDto speakerDto = new SpeakerDto();
        speakerDto.setFullName(fullName);
        speakerDto.setFile(file);
        speakerDto.setDescription_en(description_en);
        speakerDto.setDescription_ru(description_ru);
        speakerDto.setDescription_uz(description_uz);

        Speakers updatedSpeaker = speakerService.update(id, speakerDto);
        return new ResponseEntity<>(updatedSpeaker, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeaker(@PathVariable Integer id) {
        speakerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

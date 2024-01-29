package com.alibou.security.controller;

import com.alibou.security.entity.Speakers;
import com.alibou.security.exceptions.NotFoundException;
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
                                                  @RequestParam("description") String description,
                                                  @RequestParam("file") MultipartFile file) throws IOException {
        SpeakerDto speakerDto = new SpeakerDto();
        speakerDto.setFullName(fullName);
        speakerDto.setDescription(description);
        speakerDto.setFile(file);

        Speakers createdSpeaker = speakerService.create(speakerDto);
        return new ResponseEntity<>(createdSpeaker, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Speakers> updateSpeaker(@PathVariable Integer id,
                                                  @RequestParam("fullName") String fullName,
                                                  @RequestParam("description") String description,
                                                  @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        SpeakerDto speakerDto = new SpeakerDto();
        speakerDto.setFullName(fullName);
        speakerDto.setDescription(description);
        speakerDto.setFile(file);

        Speakers updatedSpeaker = speakerService.update(id, speakerDto);
        return new ResponseEntity<>(updatedSpeaker, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeaker(@PathVariable Integer id) {
        speakerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

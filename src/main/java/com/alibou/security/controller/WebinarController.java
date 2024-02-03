package com.alibou.security.controller;

import com.alibou.security.entity.Webinar;
import com.alibou.security.payload.WebinarDto;
import com.alibou.security.service.WebinarService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/webinars")
@AllArgsConstructor
public class WebinarController {

    private final WebinarService webinarService;

    @GetMapping
    public ResponseEntity<List<Webinar>> getAllWebinars() {
        List<Webinar> webinars = webinarService.findAll();
        return new ResponseEntity<>(webinars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Webinar> getWebinarById(@PathVariable Integer id) {
        Webinar webinar = webinarService.findById(id);
        return new ResponseEntity<>(webinar, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Webinar> createWebinar(
            @RequestParam("title_uz") String title_uz,
            @RequestParam("title_en") String title_en,
            @RequestParam("title_ru") String title_ru,
            @RequestParam("description_uz") String description_uz,
            @RequestParam("description_en") String description_en,
            @RequestParam("field") String field,
            @RequestParam("description_ru") String description_ru,
            @RequestParam("file") MultipartFile file,
            @RequestParam("city") String city,
            @RequestParam("online") Boolean online,
            @RequestParam("speakers") String speakers,
            @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time
    ) throws IOException {


        String[] numbersAsString = speakers.split(",");
        List<Integer> list = new ArrayList<>();
        for (String number : numbersAsString) {
            int parsedNumber = Integer.parseInt(number.trim());
            list.add(parsedNumber);
        }

        WebinarDto webinarDto = new WebinarDto();
        webinarDto.setTitle_uz(title_uz);
        webinarDto.setTitle_en(title_en);
        webinarDto.setTitle_ru(title_ru);
        webinarDto.setDescription_uz(description_uz);
        webinarDto.setDescription_en(description_en);
        webinarDto.setDescription_ru(description_ru);
        webinarDto.setFile(file);
        webinarDto.setField(field);
        webinarDto.setTime(time);
        webinarDto.setCity(city);
        webinarDto.setOnline(online);
        webinarDto.setSpeakers(list);
        System.out.println(webinarDto);

        Webinar createdWebinar = webinarService.create(webinarDto);
        return new ResponseEntity<>(createdWebinar, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Webinar> updateWebinar(
            @PathVariable Integer id,
            @RequestParam(value = "title_uz", required = false) String title_uz,
            @RequestParam(value = "title_en", required = false) String title_en,
            @RequestParam(value = "title_ru", required = false) String title_ru,
            @RequestParam(value = "field", required = false) String field,
            @RequestParam(value = "description_uz", required = false) String description_uz,
            @RequestParam(value = "description_en", required = false) String description_en,
            @RequestParam(value = "description_ru", required = false) String description_ru,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "online", required = false) Boolean online,
            @RequestParam(value = "speakers", required = false) String speakers,
            @RequestParam(value = "time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time

    ) throws IOException {

        List<Integer> list = new ArrayList<>();
        if (speakers != null) {
            String[] numbersAsString = speakers.split(",");

            for (String number : numbersAsString) {
                int parsedNumber = Integer.parseInt(number.trim());
                list.add(parsedNumber);
            }
        }

        WebinarDto webinarDto = new WebinarDto();
        webinarDto.setTitle_uz(title_uz);
        webinarDto.setTitle_en(title_en);
        webinarDto.setField(field);
        webinarDto.setTitle_ru(title_ru);
        webinarDto.setDescription_uz(description_uz);
        webinarDto.setDescription_en(description_en);
        webinarDto.setDescription_ru(description_ru);
        webinarDto.setFile(file);
        webinarDto.setCity(city);
        webinarDto.setOnline(online);
        webinarDto.setTime(time);
        webinarDto.setSpeakers(list);

        Webinar updatedWebinar = webinarService.update(id, webinarDto);
        return new ResponseEntity<>(updatedWebinar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWebinar(@PathVariable Integer id) {
        webinarService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

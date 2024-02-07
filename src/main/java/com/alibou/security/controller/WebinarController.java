package com.alibou.security.controller;

import com.alibou.security.payload.ApiResult;
import com.alibou.security.payload.WebinarDto;
import com.alibou.security.payload.WebinarDtoResponse;
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
import java.util.Set;

@RestController
@RequestMapping("/api/webinars")
@AllArgsConstructor
public class WebinarController {

    private final WebinarService webinarService;

    @GetMapping
    public ResponseEntity<List<WebinarDtoResponse>> getAllWebinars() {
        List<WebinarDtoResponse> webinars = webinarService.findAll();
        return new ResponseEntity<>(webinars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebinarDtoResponse> getWebinarById(@PathVariable Integer id) {
        WebinarDtoResponse webinar = webinarService.findById(id);
        return new ResponseEntity<>(webinar, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<String>> createWebinar(
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

        WebinarDto webinarDto = WebinarDto.builder().file(file)
                .field(field)
                .online(online)
                .description_en(description_en)
                .description_ru(description_ru)
                .description_uz(description_uz)
                .title_uz(title_uz)
                .title_en(title_en)
                .title_ru(title_ru)
                .time(time)
                .city(city)
                .field(field)
                .speakers(list).build();

        ApiResult<String> stringApiResult =
                webinarService.create(webinarDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(stringApiResult);
    }


    @PatchMapping
    public ResponseEntity<WebinarDtoResponse> updateUser(@RequestParam("webinarId") int webinarId, @RequestParam("userId") int userId) {
        WebinarDtoResponse webinarDtoResponse = webinarService.updateUser(webinarId, userId);
        return ResponseEntity.ok(webinarDtoResponse);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<WebinarDtoResponse> updateWebinar(
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
        WebinarDto webinarDto = WebinarDto.builder().file(file)
                .field(field)
                .online(online)
                .description_en(description_en)
                .description_ru(description_ru)
                .description_uz(description_uz)
                .title_uz(title_uz)
                .title_en(title_en)
                .title_ru(title_ru)
                .time(time)
                .city(city)
                .field(field)
                .speakers(list).build();


        WebinarDtoResponse updatedWebinar = webinarService.update(id, webinarDto);
        return new ResponseEntity<>(updatedWebinar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWebinar(@PathVariable Integer id) throws IOException {
        webinarService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/city")
    public ResponseEntity<Set<String>> listOfCities() {
        Set<String> set = webinarService.listOfCities();
        return ResponseEntity.ok(set);

    }

    @GetMapping("/field")
    public ResponseEntity<Set<String>> listOfFields() {
        Set<String> set = webinarService.listOfFields();
        return ResponseEntity.ok(set);

    }


}

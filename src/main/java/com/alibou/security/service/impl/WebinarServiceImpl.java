package com.alibou.security.service.impl;

import com.alibou.security.entity.Speakers;
import com.alibou.security.entity.Webinar;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.image.ImageService;
import com.alibou.security.payload.ApiResult;
import com.alibou.security.payload.UserDto;
import com.alibou.security.payload.WebinarDto;
import com.alibou.security.payload.WebinarDtoResponse;
import com.alibou.security.repository.SpeakerRepository;
import com.alibou.security.repository.WebinarRepository;
import com.alibou.security.service.WebinarService;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebinarServiceImpl implements WebinarService {

    private final WebinarRepository webinarRepository;
    private final SpeakerRepository speakerRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;

    @Override
    public List<WebinarDtoResponse> findAll() {
        return webinarRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public WebinarDtoResponse findById(Integer webinarId) {
        Webinar webinar = webinarRepository.findById(webinarId).orElseThrow(NotFoundException::new);
        return convertToDto(webinar);
    }

    @Override
    public ApiResult<String> create(WebinarDto webinarDto) throws IOException {
        List<Integer> speakerIds = webinarDto.getSpeakers();
        String image = imageService.saveImage(webinarDto.getFile());
        Set<Speakers> speakersSet = getSpeakersSetFromIds(speakerIds);
        Webinar build = Webinar.builder()
                .field(webinarDto.getField())
                .time(webinarDto.getTime())
                .link(image)
                .title_en(webinarDto.getTitle_en())
                .title_ru(webinarDto.getTitle_ru())
                .title_uz(webinarDto.getTitle_uz())
                .description_en(webinarDto.getDescription_en())
                .description_uz(webinarDto.getDescription_uz())
                .description_ru(webinarDto.getDescription_ru())
                .online(webinarDto.getOnline())
                .city(webinarDto.getCity())
                .speakers(speakersSet)
                .build();
        webinarRepository.save(build);
        return ApiResult.successResponse("created");
    }

    @Override
    public void delete(Integer webinarId) throws IOException {
        Webinar webinar = webinarRepository.findById(webinarId).orElseThrow(NotFoundException::new);
        imageService.deleteImage(webinar.getLink());
        webinarRepository.deleteById(webinarId);
    }

    @Override
    public WebinarDtoResponse update(Integer webinarId, WebinarDto webinarDto) throws IOException {
        Webinar oldWebinar = webinarRepository.findById(webinarId).orElseThrow(NotFoundException::new);
        Webinar newWebinar = buildWebinarFromDto(webinarDto);
        Webinar updatedWebinar = updateFields(oldWebinar, newWebinar);
        return convertToDto(webinarRepository.save(updatedWebinar));
    }

    @Override
    public WebinarDtoResponse updateUser(Integer webinarId, Integer userId) {
        Webinar webinar = webinarRepository.findById(webinarId).orElseThrow(NotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Set<User> users = webinar.getUser();
        users.add(user);
        webinar.setUser(users);
        return convertToDto(webinarRepository.save(webinar));
    }

    @Override
    public Set<String> listOfCities() {
        return webinarRepository.findAll()
                .stream()
                .map(Webinar::getCity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> listOfFields() {
        return webinarRepository.findAll()
                .stream()
                .map(Webinar::getField)
                .collect(Collectors.toSet());
    }

    private WebinarDtoResponse convertToDto(Webinar webinar) {
        return WebinarDtoResponse.builder()
                .userDtos(webinar.getUser().stream().map(UserDto::fromUser).collect(Collectors.toList()))
                .id(webinar.getId())
                .description_en(webinar.getDescription_en())
                .time(webinar.getTime())
                .description_ru(webinar.getDescription_ru())
                .speakers(new ArrayList<>(webinar.getSpeakers()))
                .field(webinar.getField())
                .online(webinar.getOnline())
                .description_uz(webinar.getDescription_uz())
                .file(webinar.getLink())
                .city(webinar.getCity())
                .title_uz(webinar.getTitle_uz())
                .title_ru(webinar.getTitle_ru())
                .title_en(webinar.getTitle_en())
                .build();
    }

    private Webinar updateFields(Webinar oldWebinar, Webinar newWebinar) {
        if (newWebinar.getDescription_en() != null) {
            oldWebinar.setDescription_en(newWebinar.getDescription_en());
        }
        if (newWebinar.getDescription_ru() != null) {
            oldWebinar.setDescription_ru(newWebinar.getDescription_ru());
        }
        if (newWebinar.getDescription_uz() != null) {
            oldWebinar.setDescription_uz(newWebinar.getDescription_uz());
        }
        if (newWebinar.getOnline() != null) {
            oldWebinar.setOnline(newWebinar.getOnline());
        }
        if (newWebinar.getTime() != null) {
            oldWebinar.setTime(newWebinar.getTime());
        }
        if (newWebinar.getField() != null) {
            oldWebinar.setField(newWebinar.getField());
        }
        if (newWebinar.getLink() != null) {
            oldWebinar.setLink(newWebinar.getLink());
        }
        if (newWebinar.getCity() != null) {
            oldWebinar.setCity(newWebinar.getCity());
        }
        if (newWebinar.getTitle_uz() != null) {
            oldWebinar.setTitle_uz(newWebinar.getTitle_uz());
        }
        if (newWebinar.getTitle_ru() != null) {
            oldWebinar.setTitle_ru(newWebinar.getTitle_ru());
        }
        if (newWebinar.getTitle_en() != null) {
            oldWebinar.setTitle_en(newWebinar.getTitle_en());
        }

        Set<Speakers> newSpeakers = newWebinar.getSpeakers();
        if (newSpeakers != null && !newSpeakers.isEmpty()) {
            oldWebinar.getSpeakers().clear();
            oldWebinar.getSpeakers().addAll(newSpeakers);
        }
        return oldWebinar;
    }

    private Webinar buildWebinarFromDto(WebinarDto webinarDto) throws IOException {
        List<Integer> speakerIds = webinarDto.getSpeakers();
        String image = (webinarDto.getFile() != null) ? imageService.saveImage(webinarDto.getFile()) : null;
        Set<Speakers> speakersSet = getSpeakersSetFromIds(speakerIds);

        return Webinar.builder()
                .description_en(webinarDto.getDescription_en())
                .description_ru(webinarDto.getDescription_ru())
                .speakers(speakersSet)
                .online(webinarDto.getOnline())
                .description_uz(webinarDto.getDescription_uz())
                .link(image)
                .field(webinarDto.getField())
                .time(webinarDto.getTime())
                .city(webinarDto.getCity())
                .title_uz(webinarDto.getTitle_uz())
                .title_ru(webinarDto.getTitle_ru())
                .title_en(webinarDto.getTitle_en())
                .build();
    }

    private Set<Speakers> getSpeakersSetFromIds(List<Integer> speakerIds) {
        return speakerIds.stream()
                .map(speakerId -> speakerRepository.findById(speakerId).orElseThrow(NotFoundException::new))
                .collect(Collectors.toSet());
    }
}

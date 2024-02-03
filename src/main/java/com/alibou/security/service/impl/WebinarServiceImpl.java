package com.alibou.security.service.impl;

import com.alibou.security.entity.Speakers;
import com.alibou.security.entity.Webinar;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.image.ImageService;
import com.alibou.security.payload.WebinarDto;
import com.alibou.security.repository.SpeakerRepository;
import com.alibou.security.repository.WebinarRepository;
import com.alibou.security.service.WebinarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class WebinarServiceImpl implements WebinarService {
    private final WebinarRepository webinarRepository;
    private final SpeakerRepository speakerRepository;
    private final ImageService imageService;


    @Override
    public List<Webinar> findAll() {
        return webinarRepository.findAll();
    }

    @Override
    public Webinar findById(Integer integer) {
        return webinarRepository.findById(integer).orElseThrow(NotFoundException::new);
    }

    @Override
    public Webinar create(WebinarDto entity) throws IOException {
        List<Integer> speakers = entity.getSpeakers();
        String image = imageService.saveImage(entity.getFile());
        Set<Speakers> speakersSet = new HashSet<>();
        for (int speaker : speakers) {
            Speakers speakers1 = speakerRepository.findById(speaker).orElseThrow(NotFoundException::new);
            speakersSet.add(speakers1);
        }
        Webinar build = Webinar.builder().description_en(entity.getDescription_en())
                .description_ru(entity.getDescription_ru())
                .speakers(speakersSet)
                .time(entity.getTime())
                .field(entity.getField())
                .online(entity.getOnline())
                .description_uz(entity.getDescription_uz())
                .description_en(entity.getDescription_en())
                .link(image)
                .city(entity.getCity())
                .title_uz(entity.getTitle_uz())
                .title_ru(entity.getTitle_ru())
                .title_en(entity.getTitle_en())
                .build();
        return webinarRepository.save(build);
    }


    @Override
    public void delete(Integer integer) {
        webinarRepository.deleteById(integer);

    }

    @Override
    public Webinar update(Integer integer, WebinarDto entity) throws IOException {
        Webinar oldWebinar = webinarRepository.findById(integer).orElseThrow(NotFoundException::new);
        Webinar newWebinar = buildWebinarFromDto(entity);

        Webinar updatedWebinar = check(oldWebinar, newWebinar);

        return webinarRepository.save(updatedWebinar);
    }

    private Webinar check(Webinar oldWebinar, Webinar newWebinar) {
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

    private Webinar buildWebinarFromDto(WebinarDto entity) throws IOException {
        List<Integer> speakers = entity.getSpeakers();
        // Check if the file is not null before trying to save it
        String image = entity.getFile() != null ? imageService.saveImage(entity.getFile()) : null;

        Set<Speakers> speakersSet = new HashSet<>();
        for (int speaker : speakers) {
            Speakers speakers1 = speakerRepository.findById(speaker).orElseThrow(NotFoundException::new);
            speakersSet.add(speakers1);
        }

        return Webinar.builder()
                .description_en(entity.getDescription_en())
                .description_ru(entity.getDescription_ru())
                .speakers(speakersSet)
                .online(entity.getOnline())
                .description_uz(entity.getDescription_uz())
                .link(image)
                .field(entity.getField())
                .time(entity.getTime())
                .city(entity.getCity())
                .title_uz(entity.getTitle_uz())
                .title_ru(entity.getTitle_ru())
                .title_en(entity.getTitle_en())
                .build();
    }


}

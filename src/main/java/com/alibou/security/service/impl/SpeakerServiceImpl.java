package com.alibou.security.service.impl;

import com.alibou.security.entity.Speakers;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.image.ImageService;
import com.alibou.security.payload.SpeakerDto;
import com.alibou.security.repository.SpeakerRepository;
import com.alibou.security.service.SpeakerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SpeakerServiceImpl implements SpeakerService {

    private final ImageService imageService;
    private final SpeakerRepository speakerRepository;

    @Override
    public List<Speakers> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speakers findById(Integer speakerId) {
        return speakerRepository.findById(speakerId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Speakers create(SpeakerDto entity) throws IOException {
        String link = imageService.saveImage(entity.getFile());
        log.info("Image link: {}", link);

        Speakers speakers = Speakers.builder()
                .description_uz(entity.getDescription_uz())
                .description_ru(entity.getDescription_ru())
                .description_en(entity.getDescription_en())
                .fullName(entity.getFullName())
                .link(link)
                .build();

        return speakerRepository.save(speakers);
    }

    @Override
    public Speakers update(Integer speakerId, SpeakerDto entity) throws IOException {
        Speakers speakers = speakerRepository.findById(speakerId).orElseThrow(NotFoundException::new);

        if (entity.getFile() != null) {
            speakers.setLink(imageService.saveImage(entity.getFile()));
        }
        updateIfNotNull(entity.getDescription_uz(), speakers::setDescription_uz);
        updateIfNotNull(entity.getDescription_en(), speakers::setDescription_en);
        updateIfNotNull(entity.getDescription_ru(), speakers::setDescription_ru);
        updateIfNotNull(entity.getFullName(), speakers::setFullName);

        return speakerRepository.save(speakers);
    }

    @Override
    public void delete(Integer speakerId) throws IOException {
        Speakers speakers = speakerRepository.findById(speakerId).orElseThrow(NotFoundException::new);
        imageService.deleteImage(speakers.getLink());
        speakerRepository.deleteById(speakerId);
    }

    private <T> void updateIfNotNull(T value, java.util.function.Consumer<T> updater) {
        if (value != null) {
            updater.accept(value);
        }
    }
}

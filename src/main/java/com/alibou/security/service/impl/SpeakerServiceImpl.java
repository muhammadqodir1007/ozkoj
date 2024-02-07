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

@AllArgsConstructor
@Service
@Slf4j
public class SpeakerServiceImpl implements SpeakerService {
    ImageService imageService;

    SpeakerRepository speakerRepository;

    @Override
    public List<Speakers> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speakers findById(Integer integer) {
        return speakerRepository.findById(integer).orElseThrow(NotFoundException::new);
    }


    @Override
    public Speakers create(SpeakerDto entity) throws IOException {

        String link = imageService.saveImage(entity.getFile());
        log.info("Image link: {}", link);

        Speakers speakers = Speakers.builder().
                description_uz(entity.getDescription_uz()).
                description_ru(entity.getDescription_ru()).
                description_en(entity.getDescription_en()).
                fullName(entity.getFullName()).link(link).
                build();
        return speakerRepository.save(speakers);
    }

    @Override
    public Speakers update(Integer integer, SpeakerDto entity) throws IOException {


        Speakers speakers = speakerRepository.findById(integer).orElseThrow(NotFoundException::new);
        if (entity.getFile() != null) {
            speakers.setLink(imageService.saveImage(entity.getFile()));
        }
        if (entity.getDescription_uz() != null) {
            speakers.setDescription_uz(entity.getDescription_uz());
        }
        if (entity.getDescription_en() != null) {
            speakers.setDescription_en(entity.getDescription_en());
        }
        if (entity.getDescription_ru() != null) {
            speakers.setDescription_ru(entity.getDescription_ru());
        }
        if (entity.getFullName() != null) {
            speakers.setFullName(entity.getFullName());
        }
        return speakerRepository.save(speakers);    //not finished

    }

    @Override
    public void delete(Integer integer) throws IOException {
        Speakers speakers = speakerRepository.findById(integer).orElseThrow(NotFoundException::new);
        imageService.deleteImage(speakers.getLink());
        speakerRepository.deleteById(integer);
    }


}

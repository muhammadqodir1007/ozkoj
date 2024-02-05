package com.alibou.security.service.impl;

import com.alibou.security.entity.Partners;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.image.ImageService;
import com.alibou.security.payload.PartnersDto;
import com.alibou.security.repository.PartnersRepository;
import com.alibou.security.service.PartnersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PartnersServiceImpl implements PartnersService {

    private final PartnersRepository partnersRepository;
    private final ImageService imageService;

    @Override
    public List<Partners> findAll() {
        return partnersRepository.findAll();
    }

    @Override
    public Partners findById(Integer id) {
        return partnersRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Partners create(PartnersDto entity) throws IOException {
        String link = imageService.saveImage(entity.getFile());

        Partners partners = Partners.builder()
                .link(link)
                .title_uz(entity.getTitle_uz())
                .title_ru(entity.getTitle_ru())
                .title_en(entity.getTitle_en())
                .url(entity.getUrl())
                .build();

        return partnersRepository.save(partners);
    }

    @Override
    public Partners update(Integer id, PartnersDto entity) throws IOException {
        Partners partners = partnersRepository.findById(id).orElseThrow(NotFoundException::new);

        if (entity.getFile() != null) {
            partners.setLink(imageService.saveImage(entity.getFile()));
        }

        // Check and update only if the corresponding field in PartnersDto is not null
        partners.setTitle_uz(entity.getTitle_uz() != null ? entity.getTitle_uz() : partners.getTitle_uz());
        partners.setTitle_ru(entity.getTitle_ru() != null ? entity.getTitle_ru() : partners.getTitle_ru());
        partners.setTitle_en(entity.getTitle_en() != null ? entity.getTitle_en() : partners.getTitle_en());
        partners.setUrl(entity.getUrl() != null ? entity.getUrl() : partners.getUrl());

        return partnersRepository.save(partners);
    }


    @Override
    public void delete(Integer id) {
        partnersRepository.deleteById(id);
    }
}

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

        updateIfNotNull(entity.getTitle_uz(), partners::setTitle_uz);
        updateIfNotNull(entity.getTitle_ru(), partners::setTitle_ru);
        updateIfNotNull(entity.getTitle_en(), partners::setTitle_en);
        updateIfNotNull(entity.getUrl(), partners::setUrl);

        return partnersRepository.save(partners);
    }

    @Override
    public void delete(Integer id) throws IOException {
        Partners partners = partnersRepository.findById(id).orElseThrow(NotFoundException::new);
        imageService.deleteImage(partners.getLink());
        partnersRepository.deleteById(id);
    }

    private <T> void updateIfNotNull(T value, java.util.function.Consumer<T> updater) {
        if (value != null) {
            updater.accept(value);
        }
    }
}

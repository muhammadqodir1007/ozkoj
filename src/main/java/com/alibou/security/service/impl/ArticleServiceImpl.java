package com.alibou.security.service.impl;

import com.alibou.security.entity.Article;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.image.ImageService;
import com.alibou.security.payload.ArticleDto;
import com.alibou.security.payload.NewsDto;
import com.alibou.security.repository.ArticleRepository;
import com.alibou.security.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository repository;
    ImageService imageService;


    @Override
    public List<Article> findAll() {
        return repository.findAll();
    }

    @Override
    public Article findById(Integer integer) {
        return repository.findById(integer).orElseThrow(NotFoundException::new);
    }

    @Override
    public Article create(NewsDto entity) throws IOException {
        String link = imageService.saveImage(entity.getFile());
        LocalDateTime now = LocalDateTime.now();

        Article news = Article.builder()
                .title_uz(entity.getTitle_uz())
                .title_ru(entity.getTitle_ru())
                .title_en(entity.getTitle_en())
                .description_uz(entity.getDescription_uz())
                .description_en(entity.getDescription_en())
                .description_ru(entity.getDescription_ru())
                .link(link)
                .createdDate(now)
                .build();

        return repository.save(news);
    }

    @Override
    public Article update(Integer newsId, ArticleDto entity) throws IOException {
        Article news = repository.findById(newsId).orElseThrow(NotFoundException::new);

        if (entity.getFile() != null) {
            news.setLink(imageService.saveImage(entity.getFile()));
        }

        if (entity.getTitle_uz() != null) {
            news.setTitle_uz(entity.getTitle_uz());
        }
        if (entity.getTitle_en() != null) {
            news.setTitle_en(entity.getTitle_en());
        }
        if (entity.getTitle_ru() != null) {
            news.setTitle_ru(entity.getTitle_ru());
        }

        if (entity.getDescription_uz() != null) {
            news.setDescription_uz(entity.getDescription_uz());
        }
        if (entity.getDescription_en() != null) {
            news.setDescription_en(entity.getDescription_en());
        }
        if (entity.getDescription_ru() != null) {
            news.setDescription_ru(entity.getDescription_ru());
        }

        return repository.save(news);
    }

    @Override
    public void delete(Integer integer) {
        repository.deleteById(integer);
    }
}
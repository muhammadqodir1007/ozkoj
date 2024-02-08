package com.alibou.security.service.impl;

import com.alibou.security.entity.Article;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.image.ImageService;
import com.alibou.security.payload.ArticleDto;
import com.alibou.security.repository.ArticleRepository;
import com.alibou.security.repository.NewsRepository;
import com.alibou.security.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ImageService imageService;
    private final NewsRepository newsRepository;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findById(Integer articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Article create(ArticleDto entity) throws IOException {
        String link = imageService.saveImage(entity.getFile());
        LocalDateTime now = LocalDateTime.now();

        Article article = Article.builder()
                .title_uz(entity.getTitle_uz())
                .title_ru(entity.getTitle_ru())
                .title_en(entity.getTitle_en())
                .description_uz(entity.getDescription_uz())
                .description_en(entity.getDescription_en())
                .description_ru(entity.getDescription_ru())
                .link(link)
                .createdDate(now)
                .build();

        return articleRepository.save(article);
    }

    @Override
    public Article update(Integer articleId, ArticleDto entity) throws IOException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(NotFoundException::new);

        if (entity.getFile() != null) {
            article.setLink(imageService.saveImage(entity.getFile()));
        }

        if (entity.getTitle_uz() != null) {
            article.setTitle_uz(entity.getTitle_uz());
        }
        if (entity.getTitle_en() != null) {
            article.setTitle_en(entity.getTitle_en());
        }
        if (entity.getTitle_ru() != null) {
            article.setTitle_ru(entity.getTitle_ru());
        }

        if (entity.getDescription_uz() != null) {
            article.setDescription_uz(entity.getDescription_uz());
        }
        if (entity.getDescription_en() != null) {
            article.setDescription_en(entity.getDescription_en());
        }
        if (entity.getDescription_ru() != null) {
            article.setDescription_ru(entity.getDescription_ru());
        }

        return articleRepository.save(article);
    }

    @Override
    public void delete(Integer articleId) throws IOException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(NotFoundException::new);

        imageService.deleteImage(article.getLink());
        articleRepository.deleteById(articleId);
    }
}

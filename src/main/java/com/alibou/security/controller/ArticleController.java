package com.alibou.security.controller;

import com.alibou.security.entity.Article;
import com.alibou.security.payload.ArticleDto;
import com.alibou.security.payload.NewsDto;
import com.alibou.security.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {


    private ArticleService newsService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllNews() {
        List<Article> newsList = newsService.findAll();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getNewsById(@PathVariable Integer id) {
        Article news = newsService.findById(id);
        return ResponseEntity.ok(news);
    }

    @PostMapping
    public ResponseEntity<Article> createNews(
            @RequestParam String title_uz,
            @RequestParam String title_ru,
            @RequestParam String title_en,
            @RequestParam String description_uz,
            @RequestParam String description_ru,
            @RequestParam String description_en,
            @RequestParam MultipartFile file
    ) throws IOException {
        NewsDto newsDto = new NewsDto();
        newsDto.setTitle_uz(title_uz);
        newsDto.setTitle_ru(title_ru);
        newsDto.setTitle_en(title_en);
        newsDto.setDescription_uz(description_uz);
        newsDto.setDescription_ru(description_ru);
        newsDto.setDescription_en(description_en);
        newsDto.setFile(file);

        Article createdNews = newsService.create(newsDto);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Article> updateNews(
            @PathVariable Integer id,
            @RequestParam(required = false) String title_uz,
            @RequestParam(required = false) String title_ru,
            @RequestParam(required = false) String title_en,
            @RequestParam(required = false) String description_uz,
            @RequestParam(required = false) String description_ru,
            @RequestParam(required = false) String description_en,
            @RequestParam(required = false) MultipartFile file
    ) throws IOException {
        ArticleDto newsDto = new ArticleDto();
        newsDto.setTitle_uz(title_uz);
        newsDto.setTitle_ru(title_ru);
        newsDto.setTitle_en(title_en);
        newsDto.setDescription_uz(description_uz);
        newsDto.setDescription_ru(description_ru);
        newsDto.setDescription_en(description_en);
        newsDto.setFile(file);

        Article updatedNews = newsService.update(id, newsDto);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Integer id) throws IOException {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

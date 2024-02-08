package com.alibou.security.controller;

import com.alibou.security.entity.Article;
import com.alibou.security.payload.ArticleDto;
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
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articleList = articleService.findAll();
        return ResponseEntity.ok(articleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Integer id) {
        Article article = articleService.findById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(
            @RequestParam String title_uz,
            @RequestParam String title_ru,
            @RequestParam String title_en,
            @RequestParam String description_uz,
            @RequestParam String description_ru,
            @RequestParam String description_en,
            @RequestParam MultipartFile file
    ) throws IOException {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle_uz(title_uz);
        articleDto.setTitle_ru(title_ru);
        articleDto.setTitle_en(title_en);
        articleDto.setDescription_uz(description_uz);
        articleDto.setDescription_ru(description_ru);
        articleDto.setDescription_en(description_en);
        articleDto.setFile(file);

        Article createdArticle = articleService.create(articleDto);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable Integer id,
            @RequestParam(required = false) String title_uz,
            @RequestParam(required = false) String title_ru,
            @RequestParam(required = false) String title_en,
            @RequestParam(required = false) String description_uz,
            @RequestParam(required = false) String description_ru,
            @RequestParam(required = false) String description_en,
            @RequestParam(required = false) MultipartFile file
    ) throws IOException {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle_uz(title_uz);
        articleDto.setTitle_ru(title_ru);
        articleDto.setTitle_en(title_en);
        articleDto.setDescription_uz(description_uz);
        articleDto.setDescription_ru(description_ru);
        articleDto.setDescription_en(description_en);
        articleDto.setFile(file);

        Article updatedArticle = articleService.update(id, articleDto);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) throws IOException {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

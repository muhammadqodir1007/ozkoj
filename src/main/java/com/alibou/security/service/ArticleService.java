package com.alibou.security.service;

import com.alibou.security.entity.Article;
import com.alibou.security.payload.ArticleDto;
import com.alibou.security.payload.NewsDto;

import java.io.IOException;
import java.util.List;

public interface ArticleService {


    public List<Article> findAll();

    public Article findById(Integer integer);


    public Article create(NewsDto entity) throws IOException;


    public Article update(Integer integer, ArticleDto entity) throws IOException;

    public void delete(Integer integer);


}

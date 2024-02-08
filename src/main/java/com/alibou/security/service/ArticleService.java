package com.alibou.security.service;

import com.alibou.security.entity.Article;
import com.alibou.security.payload.ArticleDto;

import java.io.IOException;
import java.util.List;

public interface ArticleService {


    List<Article> findAll();

    Article findById(Integer integer);


    Article create(ArticleDto entity) throws IOException;


    Article update(Integer integer, ArticleDto entity) throws IOException;

    void delete(Integer integer) throws IOException;


}

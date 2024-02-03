package com.alibou.security.service;

import com.alibou.security.entity.News;
import com.alibou.security.payload.NewsDto;

import java.io.IOException;
import java.util.List;

public interface NewsService {


    List<News> findAll();

    News findById(Integer integer);


    News create(NewsDto entity) throws IOException;


    News update(Integer integer, NewsDto entity) throws IOException;

    void delete(Integer integer);


}

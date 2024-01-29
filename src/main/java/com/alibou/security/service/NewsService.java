package com.alibou.security.service;

import com.alibou.security.entity.News;
import com.alibou.security.entity.Speakers;
import com.alibou.security.payload.NewsDto;

import java.io.IOException;
import java.util.List;

public interface NewsService {


    public List<News> findAll();

    public News findById(Integer integer);


    public News create(NewsDto entity) throws IOException;


    public News update(Integer integer, NewsDto entity) throws IOException;

    public void delete(Integer integer);


}

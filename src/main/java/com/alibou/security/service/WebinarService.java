package com.alibou.security.service;

import com.alibou.security.entity.Webinar;
import com.alibou.security.payload.SpeakerDto;
import com.alibou.security.payload.WebinarDto;

import java.io.IOException;
import java.util.List;

public interface WebinarService {

    List<Webinar> findAll();

    Webinar findById(Integer integer);


    Webinar create(WebinarDto entity) throws IOException;


    Webinar update(Integer integer, WebinarDto entity) throws IOException;

    void delete(Integer integer);
}

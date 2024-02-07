package com.alibou.security.service;

import com.alibou.security.entity.Speakers;
import com.alibou.security.payload.SpeakerDto;

import java.io.IOException;
import java.util.List;

public interface SpeakerService {

    List<Speakers> findAll();

    Speakers findById(Integer integer);


    Speakers create(SpeakerDto entity) throws IOException;


    Speakers update(Integer integer, SpeakerDto entity) throws IOException;

    void delete(Integer integer) throws IOException;


}

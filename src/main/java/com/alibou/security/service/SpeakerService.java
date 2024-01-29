package com.alibou.security.service;

import com.alibou.security.entity.Speakers;
import com.alibou.security.payload.SpeakerDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SpeakerService {

    public List<Speakers> findAll();

    public Speakers findById(Integer integer);


    public Speakers create(SpeakerDto entity) throws IOException;


    public Speakers update(Integer integer, SpeakerDto entity) throws IOException;

    public void delete(Integer integer);


}

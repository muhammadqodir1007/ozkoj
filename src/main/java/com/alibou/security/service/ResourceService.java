package com.alibou.security.service;

import com.alibou.security.entity.Resource;
import com.alibou.security.payload.ResourceDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ResourceService {
    List<Resource> findAll();

    Resource findById(Integer id);

    Resource create(ResourceDto resource) throws IOException;

    Resource update(Integer resourceId, ResourceDto resource) throws IOException;

    void delete(Integer id);
}

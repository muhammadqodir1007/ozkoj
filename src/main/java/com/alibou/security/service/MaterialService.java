package com.alibou.security.service;

import com.alibou.security.entity.Material;

import java.io.IOException;
import java.util.List;

public interface MaterialService {
    List<Material> findAll();

    Material findById(Integer integer);

    Material create(Material entity) throws IOException;

    Material update(Integer integer, Material entity) throws IOException;

    void delete(Integer integer);


}

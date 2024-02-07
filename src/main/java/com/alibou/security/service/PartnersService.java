package com.alibou.security.service;

import com.alibou.security.entity.Partners;
import com.alibou.security.payload.PartnersDto;

import java.io.IOException;
import java.util.List;

public interface PartnersService {

    List<Partners> findAll();

    Partners findById(Integer id);

    Partners create(PartnersDto entity) throws IOException;

    Partners update(Integer id, PartnersDto entity) throws IOException;

    void delete(Integer id) throws IOException;
}

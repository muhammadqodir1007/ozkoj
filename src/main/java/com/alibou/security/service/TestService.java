package com.alibou.security.service;

import com.alibou.security.entity.Test;
import com.alibou.security.payload.TestDto;

import java.io.IOException;
import java.util.List;

public interface TestService {
    List<Test> findAll();

    Test findById(Long id);

    Test create(TestDto resource) throws IOException;

    Test update(Long resourceId, TestDto resource) throws IOException;

    void delete(Long id) throws IOException;
}

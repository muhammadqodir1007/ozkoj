package com.alibou.security.quiz.material;

import java.io.IOException;
import java.util.List;

public interface TestService {
    List<Test> findAll();

    List<Test> findAllByMaterialId(int id);

    Test findById(Long id);

    Test create(TestDto resource) throws IOException;

    Test update(Long resourceId, TestDto resource) throws IOException;

    void delete(Long id) throws IOException;


}

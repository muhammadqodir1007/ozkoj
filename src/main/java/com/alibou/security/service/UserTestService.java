package com.alibou.security.service;

import com.alibou.security.entity.UserTest;
import com.alibou.security.payload.UserTestDto;

import java.util.List;

public interface UserTestService {
    List<UserTest> findAll();

    UserTest findById(Long id);

    UserTest create(UserTestDto userTestDto);

    UserTest update(Long userTestId, UserTestDto userTestDto);

    void delete(Long id);
}

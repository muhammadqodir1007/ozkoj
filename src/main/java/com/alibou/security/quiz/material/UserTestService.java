package com.alibou.security.quiz.material;

import java.util.List;

public interface UserTestService {
    List<UserTest> findAll();

    List<UserTest> findAllByUserId(Long id);

    UserTest findById(Long id);

    UserTest create(UserTestDto userTestDto);

    UserTest update(Long userTestId, UserTestDto userTestDto);

    void delete(Long id);
}

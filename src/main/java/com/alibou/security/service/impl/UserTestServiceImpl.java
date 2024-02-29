package com.alibou.security.service.impl;

import com.alibou.security.entity.UserTest;
import com.alibou.security.entity.UserTestAnswer;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.payload.UserTestAnswerDto;
import com.alibou.security.payload.UserTestDto;
import com.alibou.security.repository.UserTestRepository;
import com.alibou.security.service.UserTestService;
import com.alibou.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserTestServiceImpl implements UserTestService {

    private final UserTestRepository userTestRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserTest> findAll() {
        return userTestRepository.findAll();
    }

    @Override
    public UserTest findById(Long id) {
        return userTestRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public UserTest create(UserTestDto userTestDto) {
        UserTest userTest = new UserTest();
        userTest.setUserId(userTestDto.getUserId());
        userTest.setMaterialId(userTestDto.getMaterialId());
        userTest.setTestId(userTestDto.getTestId());

        // Handle answers from UserTestDto and set them in UserTest
        List<UserTestAnswer> userTestAnswers = userTestDto.getAnswers().stream()
                .map(this::mapToUserTestAnswer)
                .collect(Collectors.toList());

        userTest.setAnswers(userTestAnswers);

        return userTestRepository.save(userTest);
    }

    @Override
    public UserTest update(Long userTestId, UserTestDto userTestDto) {
        UserTest existingUserTest = userTestRepository.findById(userTestId)
                .orElseThrow(NotFoundException::new);

        existingUserTest.setUserId(userTestDto.getUserId());
        existingUserTest.setMaterialId(userTestDto.getMaterialId());
        existingUserTest.setTestId(userTestDto.getTestId());

        // Handle updates for answers from UserTestDto
        List<UserTestAnswer> updatedUserTestAnswers = userTestDto.getAnswers().stream()
                .map(this::mapToUserTestAnswer)
                .collect(Collectors.toList());

        existingUserTest.setAnswers(updatedUserTestAnswers);

        return userTestRepository.save(existingUserTest);
    }

    @Override
    public void delete(Long id) {
        userTestRepository.deleteById(id);
    }

    private UserTestAnswer mapToUserTestAnswer(UserTestAnswerDto answerDto) {
        UserTestAnswer userTestAnswer = new UserTestAnswer();
        userTestAnswer.setQuestionId(answerDto.getQuestionId());
        userTestAnswer.setAnswer(answerDto.getAnswer());
        userTestAnswer.setCorrect(answerDto.getCorrect());
        return userTestAnswer;
    }
}

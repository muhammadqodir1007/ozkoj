package com.alibou.security.service.impl;

import com.alibou.security.entity.Material;
import com.alibou.security.entity.Test;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.payload.TestDto;
import com.alibou.security.repository.MaterialRepository;
import com.alibou.security.repository.TestRepository;
import com.alibou.security.service.TestService;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final MaterialRepository materialRepository;

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Test findById(Long id) {
        return testRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Test create(TestDto resource) throws IOException {
        Material material = materialRepository.findById(resource.getMaterial()).orElseThrow(NotFoundException::new);

        Test build = Test.builder()
                .createdDate(LocalDateTime.now())
                .question(resource.getQuestion())
                .options(resource.getOptions())
                .correct(resource.getCorrect())
                .material(material)
                .build();

        return testRepository.save(build);
    }

    @Override
    public Test update(Long resourceId, TestDto resource) throws IOException {
        Test test = testRepository.findById(resourceId).orElseThrow(NotFoundException::new);
        Test updatedTest = updateTest(test, resource);
        return testRepository.save(updatedTest);
    }

    private Test updateTest(Test oldTest, TestDto newTest) {
        if (newTest.getCorrect() != null) {
            oldTest.setCorrect(newTest.getCorrect());
        }
        if (newTest.getOptions() != null) {
            oldTest.setOptions(newTest.getOptions());
        }
        if (newTest.getQuestion() != null) {
            oldTest.setQuestion(newTest.getQuestion());
        }
        return oldTest;
    }

    @Override
    public void delete(Long id) throws IOException {
        testRepository.deleteById(id);
    }
}

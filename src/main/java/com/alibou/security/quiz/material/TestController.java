package com.alibou.security.quiz.material;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.findAll();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable Long id) {
        Test test = testService.findById(id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Test> createTest(@RequestBody TestDto testDto) throws IOException {
        Test createdTest = testService.create(testDto);
        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable Long id, @RequestBody TestDto testDto) throws IOException {
        Test updatedTest = testService.update(id, testDto);
        return new ResponseEntity<>(updatedTest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) throws IOException {
        testService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

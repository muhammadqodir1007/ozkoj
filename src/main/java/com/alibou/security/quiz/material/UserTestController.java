package com.alibou.security.quiz.material;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-tests")
@AllArgsConstructor
public class UserTestController {

    private final UserTestService userTestService;

    @GetMapping
    public ResponseEntity<List<UserTest>> getAllUserTests() {
        List<UserTest> userTests = userTestService.findAll();
        return new ResponseEntity<>(userTests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTest> getUserTestById(@PathVariable Long id) {
        UserTest userTest = userTestService.findById(id);
        return new ResponseEntity<>(userTest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserTest> createUserTest(@RequestBody UserTestDto userTestDto) {
        UserTest createdUserTest = userTestService.create(userTestDto);
        return new ResponseEntity<>(createdUserTest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTest> updateUserTest(@PathVariable Long id, @RequestBody UserTestDto userTestDto) {
        UserTest updatedUserTest = userTestService.update(id, userTestDto);
        return new ResponseEntity<>(updatedUserTest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserTest(@PathVariable Long id) {
        userTestService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

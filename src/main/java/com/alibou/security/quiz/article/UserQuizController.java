package com.alibou.security.quiz.article;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-quizzes")
public class UserQuizController {

    private final UserQuizService userQuizService;

    public UserQuizController(UserQuizService userQuizService) {
        this.userQuizService = userQuizService;
    }

    @GetMapping
    public ResponseEntity<List<UserQuiz>> getAllUserQuizzes() {
        List<UserQuiz> userQuizzes = userQuizService.findAll();
        return new ResponseEntity<>(userQuizzes, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserQuiz>> getAllUserQuizzesByUserId(@PathVariable Long id) {
        List<UserQuiz> userQuizzes = userQuizService.findAllByUserId(id);
        return new ResponseEntity<>(userQuizzes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserQuiz> getUserQuizById(@PathVariable Long id) {
        UserQuiz userQuiz = userQuizService.findById(id);
        return new ResponseEntity<>(userQuiz, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserQuiz> createUserQuiz(@RequestBody UserQuizDto userQuizDto) {
        UserQuiz createdUserQuiz = userQuizService.create(userQuizDto);
        return new ResponseEntity<>(createdUserQuiz, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserQuiz> updateUserQuiz(@PathVariable Long id, @RequestBody UserQuizDto userQuizDto) {
        UserQuiz updatedUserQuiz = userQuizService.update(id, userQuizDto);
        return new ResponseEntity<>(updatedUserQuiz, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserQuiz(@PathVariable Long id) {
        userQuizService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

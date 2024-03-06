package com.alibou.security.quiz.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {


    List<UserQuiz> findAllByUserId(Long id);

}

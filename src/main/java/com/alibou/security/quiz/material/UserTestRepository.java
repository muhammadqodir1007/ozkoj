package com.alibou.security.quiz.material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {


    List<UserTest> findAllByUserId(Long id);


}

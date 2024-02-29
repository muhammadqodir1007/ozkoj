package com.alibou.security.repository;

import com.alibou.security.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {
    // You can add custom queries or methods here if needed
}

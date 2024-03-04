package com.alibou.security.repository;

import com.alibou.security.entity.Material;
import com.alibou.security.quiz.material.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {





}

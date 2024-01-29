package com.alibou.security.repository;

import com.alibou.security.entity.Speakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends JpaRepository<Speakers, Integer> {
}

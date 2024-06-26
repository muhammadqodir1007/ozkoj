package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fazo.entity.Member;

import java.util.List;

@Repository

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findAllByUserId(int id);
}

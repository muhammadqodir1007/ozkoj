package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fazo.entity.Member;
@Repository

public interface MemberRepository extends JpaRepository<Member,Long> {
}

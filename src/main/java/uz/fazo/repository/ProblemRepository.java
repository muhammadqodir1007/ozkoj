package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fazo.entity.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
}

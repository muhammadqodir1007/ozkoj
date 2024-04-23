package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.fazo.entity.Deadline;

public interface DeadlineRepository extends JpaRepository<Deadline, Long> {
}

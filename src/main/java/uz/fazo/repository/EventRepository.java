package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fazo.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}

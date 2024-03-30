package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fazo.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {


    List<Event> findAllByUserId(int id);
}

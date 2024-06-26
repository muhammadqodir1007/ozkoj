package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.fazo.entity.Material;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findAllByUserId(int id);


}

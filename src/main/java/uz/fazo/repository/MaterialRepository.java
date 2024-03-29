package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.fazo.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}

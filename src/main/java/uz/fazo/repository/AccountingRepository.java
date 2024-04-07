package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fazo.entity.Accounting;

import java.util.List;

@Repository
public interface AccountingRepository extends JpaRepository<Accounting, Long> {


    List<Accounting> findAllByUserId(int id);
}

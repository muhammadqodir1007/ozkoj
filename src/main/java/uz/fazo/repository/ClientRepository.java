package uz.fazo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.fazo.entity.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {


}

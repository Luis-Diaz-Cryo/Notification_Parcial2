package co.edu.unisabana.Notification_Parcial2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unisabana.Notification_Parcial2.entity.cliente;

@Repository
public interface CustomerRepository extends JpaRepository<cliente, Integer> {
    String findEmailById(Integer id);
}



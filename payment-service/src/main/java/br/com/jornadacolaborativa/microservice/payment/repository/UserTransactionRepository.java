package br.com.jornadacolaborativa.microservice.payment.repository;

import br.com.jornadacolaborativa.microservice.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, UUID> {
}

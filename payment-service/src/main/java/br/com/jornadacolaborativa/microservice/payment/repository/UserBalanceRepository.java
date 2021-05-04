package br.com.jornadacolaborativa.microservice.payment.repository;

import br.com.jornadacolaborativa.microservice.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}

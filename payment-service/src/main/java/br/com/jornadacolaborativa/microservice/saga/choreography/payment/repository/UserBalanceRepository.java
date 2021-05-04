package br.com.jornadacolaborativa.microservice.saga.choreography.payment.repository;

import br.com.jornadacolaborativa.microservice.saga.choreography.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}

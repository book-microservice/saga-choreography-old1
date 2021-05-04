package br.com.jornadacolaborativa.microservice.saga.choreography.inventory.repository;

import br.com.jornadacolaborativa.microservice.saga.choreography.inventory.entity.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {
}

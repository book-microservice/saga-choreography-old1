package br.com.jornadacolaborativa.microservice.saga.choreography.order.repository;

import br.com.jornadacolaborativa.microservice.saga.choreography.order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {
}

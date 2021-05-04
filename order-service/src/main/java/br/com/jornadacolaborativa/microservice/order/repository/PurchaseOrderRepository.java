package br.com.jornadacolaborativa.microservice.order.repository;

import br.com.jornadacolaborativa.microservice.order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {
}

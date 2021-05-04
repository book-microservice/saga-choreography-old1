package br.com.jornadacolaborativa.microservice.saga.choreography.order.entity;

import br.com.jornadacolaborativa.microservice.saga.choreography.events.inventory.InventoryStatus;
import br.com.jornadacolaborativa.microservice.saga.choreography.events.order.OrderStatus;
import br.com.jornadacolaborativa.microservice.saga.choreography.events.payment.PaymentStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.UUID;

@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    private UUID id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private InventoryStatus inventoryStatus;

    @Version
    private int version;

}
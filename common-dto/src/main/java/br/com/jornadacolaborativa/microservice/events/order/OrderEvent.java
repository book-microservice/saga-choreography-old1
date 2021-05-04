package br.com.jornadacolaborativa.microservice.events.order;

import java.util.Date;
import java.util.UUID;

import br.com.jornadacolaborativa.microservice.dto.PurchaseOrderDto;
import br.com.jornadacolaborativa.microservice.events.Event;
import lombok.Data;

@Data
public class OrderEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private PurchaseOrderDto purchaseOrder;
    private OrderStatus orderStatus;

    public OrderEvent() {
    }

    public OrderEvent(PurchaseOrderDto purchaseOrder, OrderStatus orderStatus) {
        this.purchaseOrder = purchaseOrder;
        this.orderStatus = orderStatus;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public PurchaseOrderDto getPurchaseOrder() {
        return purchaseOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}

package br.com.jornadacolaborativa.microservice.saga.choreography.order.service;

import br.com.jornadacolaborativa.microservice.saga.choreography.dto.PurchaseOrderDto;
import br.com.jornadacolaborativa.microservice.saga.choreography.events.order.OrderEvent;
import br.com.jornadacolaborativa.microservice.saga.choreography.events.order.OrderStatus;
import br.com.jornadacolaborativa.microservice.saga.choreography.order.entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSink;

    public void raiseOrderEvent(final PurchaseOrder purchaseOrder, OrderStatus orderStatus){
        var dto = PurchaseOrderDto.of(
                purchaseOrder.getId(),
                purchaseOrder.getProductId(),
                purchaseOrder.getPrice(),
                purchaseOrder.getUserId()
        );
        var orderEvent = new OrderEvent(dto, orderStatus);
        this.orderSink.tryEmitNext(orderEvent);
    }

}

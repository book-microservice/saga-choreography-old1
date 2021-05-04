package br.com.jornadacolaborativa.microservice.saga.choreography.order.config;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jornadacolaborativa.microservice.saga.choreography.events.inventory.InventoryStatus;
import br.com.jornadacolaborativa.microservice.saga.choreography.events.order.OrderStatus;
import br.com.jornadacolaborativa.microservice.saga.choreography.events.payment.PaymentStatus;
import br.com.jornadacolaborativa.microservice.saga.choreography.order.entity.PurchaseOrder;
import br.com.jornadacolaborativa.microservice.saga.choreography.order.repository.PurchaseOrderRepository;
import br.com.jornadacolaborativa.microservice.saga.choreography.order.service.OrderStatusPublisher;

@Service
public class OrderStatusUpdateEventHandler {

    @Autowired
    private PurchaseOrderRepository repository;

    @Autowired
    private OrderStatusPublisher publisher;

    @Transactional
    public void updateOrder(final UUID id, Consumer<PurchaseOrder> consumer){
        this.repository
                .findById(id)
                .ifPresent(consumer.andThen(this::updateOrder));

    }

    private void updateOrder(PurchaseOrder purchaseOrder){
        if(Objects.isNull(purchaseOrder.getInventoryStatus()) || Objects.isNull(purchaseOrder.getPaymentStatus()))
            return;
        var isComplete = PaymentStatus.RESERVED.equals(purchaseOrder.getPaymentStatus()) && InventoryStatus.RESERVED.equals(purchaseOrder.getInventoryStatus());
        var orderStatus = isComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isComplete){
            this.publisher.raiseOrderEvent(purchaseOrder, orderStatus);
        }
    }

}

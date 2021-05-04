package br.com.jornadacolaborativa.microservice.order.config;

import java.util.function.Consumer;

import br.com.jornadacolaborativa.microservice.events.inventory.InventoryEvent;
import br.com.jornadacolaborativa.microservice.events.payment.PaymentEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventHandlersConfig {

    @Autowired
    private OrderStatusUpdateEventHandler orderEventHandler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return pe -> {
            orderEventHandler.updateOrder(pe.getPayment().getOrderId(), po -> {
                po.setPaymentStatus(pe.getPaymentStatus());
            });
        };
    }

    @Bean
    public Consumer<InventoryEvent> inventoryEventConsumer(){
        return ie -> {
            orderEventHandler.updateOrder(ie.getInventory().getOrderId(), po -> {
                po.setInventoryStatus(ie.getStatus());
            });
        };
    }

}

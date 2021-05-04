package br.com.jornadacolaborativa.microservice.inventory.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.jornadacolaborativa.microservice.events.inventory.InventoryEvent;
import br.com.jornadacolaborativa.microservice.events.order.OrderEvent;
import br.com.jornadacolaborativa.microservice.events.order.OrderStatus;
import br.com.jornadacolaborativa.microservice.inventory.service.InventoryService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class InventoryConfig {

    @Autowired
    private InventoryService service;

    @Bean
    public Function<Flux<OrderEvent>, Flux<InventoryEvent>> inventoryProcessor() {
        return flux -> flux.flatMap(this::processInventory);
    }

    private Mono<InventoryEvent> processInventory(OrderEvent event){
        if(event.getOrderStatus().equals(OrderStatus.ORDER_CREATED)){
            return Mono.fromSupplier(() -> this.service.newOrderInventory(event));
        }
        return Mono.fromRunnable(() -> this.service.cancelOrderInventory(event));
    }

}


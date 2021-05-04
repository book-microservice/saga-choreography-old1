package br.com.jornadacolaborativa.microservice.payment.config;

import br.com.jornadacolaborativa.microservice.events.order.OrderEvent;
import br.com.jornadacolaborativa.microservice.events.order.OrderStatus;
import br.com.jornadacolaborativa.microservice.events.payment.PaymentEvent;
import br.com.jornadacolaborativa.microservice.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConfig {

    @Autowired
    private PaymentService service;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return flux -> flux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent event){
        if(event.getOrderStatus().equals(OrderStatus.ORDER_CREATED)){
            return Mono.fromSupplier(() -> this.service.newOrderEvent(event));
        }else{
            return Mono.fromRunnable(() -> this.service.cancelOrderEvent(event));
        }
    }

}

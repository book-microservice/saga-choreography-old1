package br.com.jornadacolaborativa.microservice.saga.choreography.order.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornadacolaborativa.microservice.saga.choreography.dto.OrderRequestDto;
import br.com.jornadacolaborativa.microservice.saga.choreography.order.entity.PurchaseOrder;
import br.com.jornadacolaborativa.microservice.saga.choreography.order.service.OrderCommandService;
import br.com.jornadacolaborativa.microservice.saga.choreography.order.service.OrderQueryService;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderCommandService commandService;

    @Autowired
    private OrderQueryService queryService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto requestDTO){
        requestDTO.setOrderId(UUID.randomUUID());
        return this.commandService.createOrder(requestDTO);
    }

    @GetMapping("/all")
    public List<PurchaseOrder> getOrders(){
        return this.queryService.getAll();
    }

}

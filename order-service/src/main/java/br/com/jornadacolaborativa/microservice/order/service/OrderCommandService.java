package br.com.jornadacolaborativa.microservice.order.service;

import br.com.jornadacolaborativa.microservice.dto.OrderRequestDto;
import br.com.jornadacolaborativa.microservice.events.order.OrderStatus;
import br.com.jornadacolaborativa.microservice.order.entity.PurchaseOrder;
import br.com.jornadacolaborativa.microservice.order.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OrderCommandService {

    @Autowired
    private Map<Integer, Integer> productPriceMap;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderStatusPublisher publisher;

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDTO){
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.save(this.dtoToEntity(orderRequestDTO));
        this.publisher.raiseOrderEvent(purchaseOrder, OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }

    private PurchaseOrder dtoToEntity(final OrderRequestDto dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(dto.getOrderId());
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(productPriceMap.get(purchaseOrder.getProductId()));
        return purchaseOrder;
    }

}

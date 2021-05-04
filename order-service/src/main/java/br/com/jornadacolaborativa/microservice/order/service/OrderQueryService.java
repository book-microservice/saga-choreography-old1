package br.com.jornadacolaborativa.microservice.order.service;

import br.com.jornadacolaborativa.microservice.order.entity.PurchaseOrder;
import br.com.jornadacolaborativa.microservice.order.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> getAll() {
        return this.purchaseOrderRepository.findAll();
    }

}

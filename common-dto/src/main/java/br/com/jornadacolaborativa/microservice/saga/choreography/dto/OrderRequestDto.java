package br.com.jornadacolaborativa.microservice.saga.choreography.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequestDto {

    private Integer userId;
    private Integer productId;
    private UUID orderId;

}
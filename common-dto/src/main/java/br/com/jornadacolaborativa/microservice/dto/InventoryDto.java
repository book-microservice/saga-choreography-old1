package br.com.jornadacolaborativa.microservice.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class InventoryDto {

    private UUID orderId;
    private Integer productId;

}

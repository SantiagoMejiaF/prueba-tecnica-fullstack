package com.litethinking.infrastructure.adapters.in.rest.controllers.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @Schema(description = "Id del cliente que realizó la orden", example = "1")
    @NotNull(message = "El id del cliente es obligatorio.")
    private Long customerId;

    @Schema(description = "Ids de los productos asociados a la orden", example = "[1, 2, 3]")
    @NotNull(message = "Se debe proporcionar al menos un producto.")
    private List<Long> productIds;

    @Schema(description = "Total de la orden", example = "400000")
    @NotNull(message = "El total es obligatorio.")
    private Double total;
}


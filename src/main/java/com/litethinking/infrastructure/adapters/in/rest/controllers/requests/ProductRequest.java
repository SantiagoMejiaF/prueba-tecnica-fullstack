package com.litethinking.infrastructure.adapters.in.rest.controllers.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRequest {

    @Schema(description = "Código del producto", example = "0001")
    @NotNull(message = "El código del producto es obligatorio.")
    private Long code;

    @Schema(description = "Nombre del producto", example = "Producto A")
    @NotBlank(message = "El nombre del producto es obligatorio.")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres.")
    private String name;

    @Schema(description = "Características del producto", example = "Hecho en madera de alta calidad")
    @Size(max = 100, message = "Las características no pueden exceder los 100 caracteres.")
    private String features;

    @Schema(description = "Precio del producto", example = "400000")
    @NotNull(message = "El precio es obligatorio.")
    private Double price;

    @Schema(description = "Id de la empresa que vende el producto", example = "1000286139")
    @NotBlank(message = "El NIT de la empresa es obligatorio.")
    @Size(min = 10, max = 10, message = "El NIT debe tener exactamente 10 caracteres.")
    @Pattern(regexp = "^\\d{10}$", message = "El NIT debe contener exactamente 10 dígitos numéricos.")
    private String companyNit;

    @Schema(description = "Ids de las categorías asociadas al producto", example = "[1, 2, 3]")
    @NotNull(message = "Se debe proporcionar al menos una categoría.")
    private List<Long> categoryIds;
}

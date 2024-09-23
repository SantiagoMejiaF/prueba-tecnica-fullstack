package com.litethinking.infrastructure.adapters.in.rest.controllers.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyUpdateRequest {

    @Schema(description = "Nombre de la empresa.", example = "Litethinking S.A.S.")
    @NotBlank(message = "El nombre de la empresa es obligatorio.")
    @Size(max = 30, message = "El nombre de la empresa no puede exceder los 30 caracteres.")
    private String name;

    @Schema(description = "Dirección física de la empresa.", example = "Calle 123 # 45-67")
    @NotBlank(message = "La dirección es obligatoria.")
    @Size(max = 50, message = "La dirección no puede exceder los 50 caracteres.")
    private String address;

    @Schema(description = "Número telefónico de la empresa. Debe tener exactamente 10 caracteres.", example = "3112494942")
    @NotBlank(message = "El teléfono es obligatorio.")
    @Size(min = 10, max = 10, message = "El teléfono debe tener exactamente 10 caracteres.")
    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe contener exactamente 10 dígitos numéricos.")
    private String phone;
}

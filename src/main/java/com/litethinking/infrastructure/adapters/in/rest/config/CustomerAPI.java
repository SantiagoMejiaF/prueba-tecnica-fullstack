package com.litethinking.infrastructure.adapters.in.rest.config;

import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CustomerRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Servicios relacionados con la gestión de clientes.")
public interface CustomerAPI {

    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Request inválido")
    })
    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest);

    @Operation(summary = "Obtener un cliente por ID", description = "Obtiene los detalles de un cliente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id);

    @Operation(summary = "Listar todos los clientes", description = "Obtiene una lista de todos los clientes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito")
    })
    @GetMapping
    ResponseEntity<List<CustomerResponse>> getAllCustomers();

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long id);
}

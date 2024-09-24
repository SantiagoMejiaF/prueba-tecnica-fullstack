package com.litethinking.infrastructure.adapters.in.rest.config;

import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.OrderRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Órdenes", description = "Servicios relacionados con la gestión de órdenes.")
public interface OrderAPI {

    @Operation(summary = "Crear una nueva orden", description = "Registra una nueva orden.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orden creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Request inválido")
    })
    @PostMapping
    ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest);

    @Operation(summary = "Actualizar una orden", description = "Actualiza una orden existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @PutMapping("/{id}")
    ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest);

    @Operation(summary = "Eliminar una orden", description = "Elimina una orden.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Orden eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable Long id);

    @Operation(summary = "Obtener una orden por id", description = "Obtiene los detalles de una orden.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @GetMapping("/{id}")
    ResponseEntity<OrderResponse> getOrder(@PathVariable Long id);

    @Operation(summary = "Listar todas las órdenes", description = "Obtiene una lista de todas las órdenes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito")
    })
    @GetMapping("/list")
    ResponseEntity<List<OrderResponse>> listOrders();
}

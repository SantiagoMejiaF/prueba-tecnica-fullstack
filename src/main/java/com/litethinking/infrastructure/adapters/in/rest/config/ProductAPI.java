package com.litethinking.infrastructure.adapters.in.rest.config;

import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.ProductRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productos", description = "Servicios relacionados con la gestión de productos.")
public interface ProductAPI {

    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto asociado a una empresa en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Request inválido")
    })
    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest);

    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente por su código.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{code}")
    ResponseEntity<ProductResponse> updateProduct(@PathVariable Long code, @Valid @RequestBody ProductRequest productRequest);

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto por su código.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{code}")
    ResponseEntity<Void> deleteProduct(@PathVariable Long code);

    @Operation(summary = "Obtener un producto por código", description = "Obtiene los detalles de un producto por su código.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{code}")
    ResponseEntity<ProductResponse> getProduct(@PathVariable Long code);

    @Operation(summary = "Listar todos los productos", description = "Obtiene una lista de todos los productos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito")
    })
    @GetMapping("/list")
    ResponseEntity<List<ProductResponse>> listProducts();
}

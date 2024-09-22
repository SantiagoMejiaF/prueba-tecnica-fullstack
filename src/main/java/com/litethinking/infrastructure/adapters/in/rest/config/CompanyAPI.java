package com.litethinking.infrastructure.adapters.in.rest.config;

import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CompanyRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CompanyResponse;
import com.pagatodo.commons.rest.payload.ApiErrorPayload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

public interface CompanyAPI {

    @Operation(summary = "Crear una nueva empresa", description = "Registra una nueva empresa en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa creada con éxito", content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Request inválido", content = @Content(schema = @Schema(implementation = ApiErrorPayload.class)))
    })
    @PostMapping
    ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyRequest companyRequest);


    @Operation(summary = "Actualizar una empresa", description = "Actualiza los datos de una empresa existente por NIT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa actualizada con éxito", content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content(schema = @Schema(implementation = ApiErrorPayload.class)))
    })
    @PutMapping("/{nit}")
    ResponseEntity<CompanyResponse> updateCompany(@PathVariable String nit, @Valid @RequestBody CompanyRequest companyRequest);


    @Operation(summary = "Eliminar una empresa", description = "Elimina una empresa por NIT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empresa eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content(schema = @Schema(implementation = ApiErrorPayload.class)))
    })
    @DeleteMapping("/{nit}")
    ResponseEntity<Void> deleteCompany(@PathVariable String nit);


    @Operation(summary = "Obtener una empresa por NIT", description = "Obtiene los detalles de una empresa por NIT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito", content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content(schema = @Schema(implementation = ApiErrorPayload.class)))
    })
    @GetMapping("/{nit}")
    ResponseEntity<CompanyResponse> getCompany(@PathVariable String nit);


    @Operation(summary = "Listar todas las empresas", description = "Obtiene una lista de todas las empresas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito", content = @Content(schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/list")
    ResponseEntity<List<CompanyResponse>> listCompanies();
}

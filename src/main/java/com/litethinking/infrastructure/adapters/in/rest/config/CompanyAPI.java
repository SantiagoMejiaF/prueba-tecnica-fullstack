package com.litethinking.infrastructure.adapters.in.rest.config;

import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CompanyRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CompanyUpdateRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CompanyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Tag(name = "Empresas", description = "Servicios relacionados con la gestión de empresas.")
public interface CompanyAPI {

    @Operation(summary = "Crear una nueva empresa", description = "Registra una nueva empresa en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa creada con éxito", content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Request inválido", content = @Content(schema = @Schema(implementation = CompanyResponse.class)))
    })
    @PostMapping
    ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyRequest companyRequest);


    @Operation(summary = "Actualizar una empresa", description = "Actualiza los datos de una empresa existente por Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa actualizada con éxito", content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content(schema = @Schema(implementation = CompanyResponse.class)))
    })
    @PutMapping("/{nit}")
    ResponseEntity<CompanyResponse> updateCompany(@PathVariable String nit, @Valid @RequestBody CompanyUpdateRequest companyUpdateRequest);


    @Operation(summary = "Eliminar una empresa", description = "Elimina una empresa por Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empresa eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content(schema = @Schema(implementation = CompanyResponse.class)))
    })
    @DeleteMapping("/{nit}")
    ResponseEntity<Void> deleteCompany(@PathVariable String nit);


    @Operation(summary = "Obtener una empresa por Id", description = "Obtiene los detalles de una empresa por Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito", content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada", content = @Content(schema = @Schema(implementation = CompanyResponse.class)))
    })
    @GetMapping("/{nit}")
    ResponseEntity<CompanyResponse> getCompanyByNit(@PathVariable String nit);


    @Operation(summary = "Listar todas las empresas", description = "Obtiene una lista de todas las empresas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito", content = @Content(schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/list")
    ResponseEntity<List<CompanyResponse>> listCompanies();
}

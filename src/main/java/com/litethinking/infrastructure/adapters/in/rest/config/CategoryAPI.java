package com.litethinking.infrastructure.adapters.in.rest.config;

import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Tag(name = "Categorías", description = "Servicios relacionados con la gestión de categorias")
public interface CategoryAPI {

    @Operation(summary = "Listar todas las categorias", description = "Obtiene una lista con todas las categorias.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada con éxito")
    })
    @GetMapping("/list")
    ResponseEntity<List<CategoryResponse>> listCategories();
}

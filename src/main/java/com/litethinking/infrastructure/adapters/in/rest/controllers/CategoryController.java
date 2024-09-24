package com.litethinking.infrastructure.adapters.in.rest.controllers;

import com.litethinking.domain.Category;
import com.litethinking.infrastructure.adapters.in.rest.config.CategoryAPI;
import com.litethinking.infrastructure.adapters.in.rest.controllers.mappers.CategoryMapper;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CategoryResponse;
import com.litethinking.infrastructure.ports.in.CategoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.category}")
public class CategoryController implements CategoryAPI {

    private final CategoryUseCase categoryUseCase;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseEntity<List<CategoryResponse>> listCategories() {
        List<Category> categories = categoryUseCase.getAllCategories();
        return ResponseEntity.ok(categoryMapper.toResponses(categories));
    }
}

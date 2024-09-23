package com.litethinking.infrastructure.adapters.in.rest.controllers;

import com.litethinking.domain.Product;
import com.litethinking.infrastructure.adapters.in.rest.config.ProductAPI;
import com.litethinking.infrastructure.adapters.in.rest.controllers.mappers.ProductMapper;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.ProductRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.ProductResponse;
import com.litethinking.infrastructure.ports.in.ProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.product}")
public class ProductController implements ProductAPI {

    private final ProductUseCase productUseCase;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        String companyNit = productRequest.getCompanyNit();
        List<Long> categoryIds = productRequest.getCategoryIds();
        Product product = productMapper.toDomain(productRequest);
        Product createdProduct = productUseCase.createProduct(product, companyNit, categoryIds);
        return new ResponseEntity<>(productMapper.toResponse(createdProduct), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(Long code, ProductRequest productRequest) {
        Product product = productMapper.toDomain(productRequest);
        String companyNit = productRequest.getCompanyNit();
        List<Long> categoryIds = productRequest.getCategoryIds();
        Product updatedProduct = productUseCase.updateProduct(code, product, companyNit, categoryIds);
        return ResponseEntity.ok(productMapper.toResponse(updatedProduct));
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long code) {
        productUseCase.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponse> getProduct(Long code) {
        Optional<Product> product = productUseCase.getProductByCode(code);
        return product.map(p -> ResponseEntity.ok(productMapper.toResponse(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<ProductResponse>> listProducts() {
        List<Product> products = productUseCase.getAllProducts();
        List<ProductResponse> productResponses = products.stream()
                .map(productMapper::toResponse)
                .toList();
        return ResponseEntity.ok(productResponses);
    }
}

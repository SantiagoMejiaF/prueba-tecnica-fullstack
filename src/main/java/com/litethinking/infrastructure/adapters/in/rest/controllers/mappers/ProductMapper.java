package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.Product;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.ProductRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain(ProductRequest productRequest);

    ProductResponse toResponse(Product product);
}


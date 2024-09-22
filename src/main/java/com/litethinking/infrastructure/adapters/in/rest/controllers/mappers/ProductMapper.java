package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.Product;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.ProductRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain(ProductRequest productRequest);

    ProductRequest toRequest(Product product);
}

package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Product;
import com.litethinking.infrastructure.adapters.out.database.entities.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductOutMapper {

    Product toDomain(ProductEntity productEntity);

    List<Product> toDomains(List<ProductEntity> productEntities);

    ProductEntity toEntity(Product product);
}


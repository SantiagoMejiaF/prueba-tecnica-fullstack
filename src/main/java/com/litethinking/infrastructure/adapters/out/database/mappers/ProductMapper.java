package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Product;
import com.litethinking.infrastructure.adapters.out.database.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain (ProductEntity productEntity);

    ProductEntity toEntity (Product product);
}

package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Category;
import com.litethinking.infrastructure.adapters.out.database.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toDomain (CategoryEntity categoryEntity);

    CategoryEntity toEntity (Category category);
}

package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Category;
import com.litethinking.infrastructure.adapters.out.database.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryOutMapper {

    Category toDomain (CategoryEntity categoryEntity);

    List<Category> toDomains(List<CategoryEntity> categoryEntities);

    CategoryEntity toEntity (Category category);
}

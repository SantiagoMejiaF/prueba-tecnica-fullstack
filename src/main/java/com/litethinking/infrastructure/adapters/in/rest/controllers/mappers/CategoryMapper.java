package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.Category;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CategoryRequest;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface CategoryMapper {

    Category toDomain(CategoryRequest categoryRequest);

    CategoryRequest toRequest(Category category);
}

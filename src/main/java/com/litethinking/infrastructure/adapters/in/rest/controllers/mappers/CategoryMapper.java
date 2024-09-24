package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.Category;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryResponse> toResponses(List<Category> category);
}

package com.litethinking.infrastructure.ports.in;

import com.litethinking.domain.Category;

import java.util.List;

public interface CategoryUseCase {

    List<Category> getAllCategories();
}

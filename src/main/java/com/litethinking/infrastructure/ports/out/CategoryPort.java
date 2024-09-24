package com.litethinking.infrastructure.ports.out;

import com.litethinking.domain.Category;

import java.util.List;

public interface CategoryPort {

    List<Category> findAllById(List<Long> ids);

    List<Category> findAll();
}

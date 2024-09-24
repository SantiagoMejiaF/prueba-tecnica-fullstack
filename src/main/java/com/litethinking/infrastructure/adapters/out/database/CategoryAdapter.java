package com.litethinking.infrastructure.adapters.out.database;

import com.litethinking.domain.Category;
import com.litethinking.infrastructure.adapters.out.database.mappers.CategoryOutMapper;
import com.litethinking.infrastructure.adapters.out.database.repository.CategoryRepository;
import com.litethinking.infrastructure.ports.out.CategoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CategoryAdapter implements CategoryPort {

    private final CategoryRepository categoryRepository;
    private final CategoryOutMapper categoryOutMapper;

    @Override
    public List<Category> findAllById(List<Long> ids) {

        return categoryOutMapper.toDomains(categoryRepository.findAllById(ids));
    }

    @Override
    public List<Category> findAll() {
        return categoryOutMapper.toDomains(categoryRepository.findAll());
    }
}

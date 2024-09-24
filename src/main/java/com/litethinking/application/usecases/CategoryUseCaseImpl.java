package com.litethinking.application.usecases;

import com.litethinking.domain.Category;
import com.litethinking.infrastructure.ports.in.CategoryUseCase;
import com.litethinking.infrastructure.ports.out.CategoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryUseCaseImpl implements CategoryUseCase {

    private final CategoryPort categoryPort;

    @Override
    public List<Category> getAllCategories() {
        return categoryPort.findAll();
    }
}

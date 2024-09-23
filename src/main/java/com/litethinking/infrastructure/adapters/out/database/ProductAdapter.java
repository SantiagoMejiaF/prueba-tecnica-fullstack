package com.litethinking.infrastructure.adapters.out.database;

import com.litethinking.domain.Product;
import com.litethinking.infrastructure.adapters.out.database.entities.ProductEntity;
import com.litethinking.infrastructure.adapters.out.database.mappers.ProductOutMapper;
import com.litethinking.infrastructure.adapters.out.database.repository.ProductRepository;
import com.litethinking.infrastructure.ports.out.ProductPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductAdapter implements ProductPort {

    private final ProductRepository productRepository;
    private final ProductOutMapper productOutMapper;

    @Override
    public Product save(Product product) {
        ProductEntity savedEntity = productRepository.save(productOutMapper.toEntity(product));
        return productOutMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findByCode(Long code) {
        return productRepository.findById(code).map(productOutMapper::toDomain);
    }

    @Override
    public void deleteByCode(Long code) {
        productRepository.deleteById(code);
    }

    @Override
    public List<Product> findAll() {
        return productOutMapper.toDomains(productRepository.findAll());
    }

    @Override
    public List<Product> findAllById(List<Long> ids) {
        List<ProductEntity> productEntities = productRepository.findAllById(ids);
        return productOutMapper.toDomains(productEntities);
    }
}

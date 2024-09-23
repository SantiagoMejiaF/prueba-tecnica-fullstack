package com.litethinking.infrastructure.ports.out;

import com.litethinking.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductPort {

    Product save(Product product);

    Optional<Product> findByCode(Long code);

    void deleteByCode(Long code);

    List<Product> findAll();

    List<Product> findAllById(List<Long> ids);
}



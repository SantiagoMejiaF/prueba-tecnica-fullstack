package com.litethinking.infrastructure.ports.out;

import com.litethinking.domain.Product;
import java.util.Optional;

public interface ProductPort {

    Product save(Product product);

    Optional<Product> findById(Long id);

    void deleteById(Long id);
}


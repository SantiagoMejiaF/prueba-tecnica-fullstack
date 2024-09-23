package com.litethinking.infrastructure.ports.in;

import com.litethinking.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductUseCase {

    Optional<Product> getProductByCode(Long code);

    List<Product> getAllProducts();

    Product createProduct(Product product, String companyNit, List<Long> categoryIds);

    Product updateProduct(Long code, Product product, String companyNit, List<Long> categoryIds);

    void deleteProduct(Long code);
}

package com.litethinking.application.usecases;

import com.litethinking.domain.Category;
import com.litethinking.domain.Company;
import com.litethinking.domain.Product;
import com.litethinking.infrastructure.ports.in.ProductUseCase;
import com.litethinking.infrastructure.ports.out.CategoryPort;
import com.litethinking.infrastructure.ports.out.CompanyPort;
import com.litethinking.infrastructure.ports.out.ProductPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductPort productPort;
    private final CompanyPort companyPort;
    private final CategoryPort categoryPort;

    @Override
    public Optional<Product> getProductByCode(Long code) {
        return productPort.findByCode(code);
    }

    @Override
    public List<Product> getAllProducts() {
        return productPort.findAll();
    }

    @Override
    public Product createProduct(Product product, String companyNit, List<Long> categoryIds) {

        Company company = companyPort.findByNit(companyNit)
                .orElseThrow(() -> new EntityNotFoundException("La empresa con NIT:  " + companyNit +
                        " no se encontró en la base de datos"));

        List<Category> categories = categoryPort.findAllById(categoryIds);

        if (categories.isEmpty()){
            throw new EntityNotFoundException("No se encontraron categorías con los IDs proporcionados");
        }

        Product newProduct = new Product();
        newProduct.setCode(product.getCode());
        newProduct.setName(product.getName());
        newProduct.setFeatures(product.getFeatures());
        newProduct.setPrice(product.getPrice());
        newProduct.setCompany(company);
        newProduct.setCategories(categories);

        return productPort.save(newProduct);
    }

    @Transactional
    @Override
    public Product updateProduct(Long code, Product product, String companyNit, List<Long> categoryIds) {

        Product existingProduct = productPort.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("El producto con código: " + code + " no se encontró en la base de datos"));

        Company company = companyPort.findByNit(companyNit)
                .orElseThrow(() -> new EntityNotFoundException("La empresa con NIT: " + companyNit + " no se encontró en la base de datos"));

        List<Category> categories = categoryPort.findAllById(categoryIds);
        if (categories.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron categorías con los IDs proporcionados");
        }

        existingProduct.setName(product.getName());
        existingProduct.setFeatures(product.getFeatures());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCompany(company);
        existingProduct.setCategories(categories);

        return productPort.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long code) {
        productPort.deleteByCode(code);
    }
}

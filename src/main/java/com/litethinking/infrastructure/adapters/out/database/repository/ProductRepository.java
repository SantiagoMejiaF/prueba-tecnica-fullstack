package com.litethinking.infrastructure.adapters.out.database.repository;

import com.litethinking.infrastructure.adapters.out.database.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long id);

    void deleteById(Long id);
}

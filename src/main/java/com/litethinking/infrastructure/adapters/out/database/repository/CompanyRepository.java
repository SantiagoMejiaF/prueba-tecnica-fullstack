package com.litethinking.infrastructure.adapters.out.database.repository;

import com.litethinking.infrastructure.adapters.out.database.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

    Optional<CompanyEntity> findByNit(String nit);

    void deleteByNit(String nit);
}


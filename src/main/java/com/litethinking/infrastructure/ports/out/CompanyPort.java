package com.litethinking.infrastructure.ports.out;

import com.litethinking.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyPort {

    Company save(Company company);

    Optional<Company> findByNit(String nit);

    void deleteByNit(String nit);

    List<Company> findAll();
}

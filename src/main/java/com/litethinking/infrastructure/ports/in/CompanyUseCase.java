package com.litethinking.infrastructure.ports.in;

import com.litethinking.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyUseCase {

    Optional<Company> getCompanyByNit(String nit);

    List<Company> getAllCompanies();

    Company createCompany(Company company);

    Optional<Company> updateCompany(String nit, Company company);

    void deleteCompanyByNit(String nit);
}

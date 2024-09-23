package com.litethinking.application.usecases;

import com.litethinking.domain.Company;
import com.litethinking.infrastructure.ports.in.CompanyUseCase;
import com.litethinking.infrastructure.ports.out.CompanyPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyUseCaseImpl implements CompanyUseCase {

    private final CompanyPort companyPort;

    @Override
    public Company createCompany(Company company) {
        return companyPort.save(company);
    }

    @Override
    public Optional<Company> updateCompany(String nit, Company company) {
        return companyPort.findByNit(nit).map(existingCompany -> {
            existingCompany.setName(company.getName());
            existingCompany.setAddress(company.getAddress());
            existingCompany.setPhone(company.getPhone());
            return companyPort.save(existingCompany);
        });
    }

    @Override
    public void deleteCompanyByNit(String nit) {
        companyPort.deleteByNit(nit);
    }

    @Override
    public Optional<Company> getCompanyByNit(String nit) {
        return companyPort.findByNit(nit);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyPort.findAll();
    }
}

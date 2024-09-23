package com.litethinking.infrastructure.adapters.out.database;

import com.litethinking.domain.Company;
import com.litethinking.infrastructure.adapters.out.database.entities.CompanyEntity;
import com.litethinking.infrastructure.adapters.out.database.mappers.CompanyOutMapper;
import com.litethinking.infrastructure.adapters.out.database.repository.CompanyRepository;
import com.litethinking.infrastructure.ports.out.CompanyPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CompanyAdapter implements CompanyPort {

    private final CompanyRepository companyRepository;

    private final CompanyOutMapper companyOutMapper;

    @Override
    public Company save(Company company) {
        CompanyEntity savedEntity = companyRepository.save(companyOutMapper.toEntity(company));
        return companyOutMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Company> findByNit(String nit) {
        return companyRepository.findById(nit).map(companyOutMapper::toDomain);
    }

    @Override
    public void deleteByNit(String nit) {
        companyRepository.deleteById(nit);
    }

    @Override
    public List<Company> findAll() {
        return companyOutMapper.toDomains(companyRepository.findAll());
    }
}

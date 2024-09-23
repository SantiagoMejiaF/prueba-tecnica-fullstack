package com.litethinking.infrastructure.adapters.in.rest.controllers;

import com.litethinking.domain.Company;
import com.litethinking.infrastructure.adapters.in.rest.config.CompanyAPI;
import com.litethinking.infrastructure.adapters.in.rest.controllers.mappers.CompanyMapper;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CompanyRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CompanyUpdateRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CompanyResponse;
import com.litethinking.infrastructure.ports.in.CompanyUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.company}")
public class CompanyController implements CompanyAPI {

    private final CompanyUseCase companyServiceUseCase;
    private final CompanyMapper companyMapper;

    @Override
    public ResponseEntity<CompanyResponse> createCompany(CompanyRequest companyRequest) {
        Company company = companyMapper.toDomain(companyRequest);
        Company createdCompany = companyServiceUseCase.createCompany(company);
        return new ResponseEntity<>(companyMapper.toResponse(createdCompany), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CompanyResponse> updateCompany(String nit, @Valid CompanyUpdateRequest companyUpdateRequest) {
        Company company = companyMapper.requestToDomain(companyUpdateRequest);
        Optional<Company> updatedCompany = companyServiceUseCase.updateCompany(nit, company);
        return updatedCompany.map(c -> ResponseEntity.ok(companyMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteCompany(String nit) {
        companyServiceUseCase.deleteCompanyByNit(nit);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CompanyResponse> getCompanyByNit(String nit) {
        Optional<Company> company = companyServiceUseCase.getCompanyByNit(nit);
        return company.map(c -> ResponseEntity.ok(companyMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<CompanyResponse>> listCompanies() {
        List<Company> companies = companyServiceUseCase.getAllCompanies();
        List<CompanyResponse> companyResponses = companies.stream()
                .map(companyMapper::toResponse)
                .toList();
        return ResponseEntity.ok(companyResponses);
    }
}

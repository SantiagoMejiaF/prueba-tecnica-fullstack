package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.Company;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CompanyRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CompanyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toDomain(CompanyRequest companyRequest);

    CompanyRequest toRequest(Company company);

    CompanyResponse toResponse(Company company);
}

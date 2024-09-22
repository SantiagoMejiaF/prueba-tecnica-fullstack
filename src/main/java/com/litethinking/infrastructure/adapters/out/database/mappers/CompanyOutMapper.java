package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Company;
import com.litethinking.infrastructure.adapters.out.database.entities.CompanyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyOutMapper {

    Company toDomain (CompanyEntity companyEntity);

    List <Company> toDomains (List<CompanyEntity> companyEntities);

    CompanyEntity toEntity (Company company);
}

package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Customer;
import com.litethinking.infrastructure.adapters.out.database.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toDomain (CustomerEntity customerEntity);

    CustomerEntity toEntity (Customer customer);
}

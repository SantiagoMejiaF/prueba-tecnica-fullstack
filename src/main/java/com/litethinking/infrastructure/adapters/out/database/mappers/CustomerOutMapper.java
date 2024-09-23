package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Customer;
import com.litethinking.infrastructure.adapters.out.database.entities.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerOutMapper {

    Customer toDomain(CustomerEntity customerEntity);

    CustomerEntity toEntity(Customer customer);

    List<Customer> toDomains(List<CustomerEntity> customerEntities);
}

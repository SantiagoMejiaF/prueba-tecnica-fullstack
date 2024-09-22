package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.Customer;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CustomerRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toDomain(CustomerRequest customerRequest);

    CustomerRequest toRequest(Customer customer);
}

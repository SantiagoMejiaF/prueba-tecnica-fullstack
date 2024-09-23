package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.Customer;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CustomerRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toDomain(CustomerRequest customerRequest);

    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponses(List<Customer> customers);
}

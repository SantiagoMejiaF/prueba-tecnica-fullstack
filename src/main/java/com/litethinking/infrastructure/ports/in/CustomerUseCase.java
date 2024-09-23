package com.litethinking.infrastructure.ports.in;

import com.litethinking.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerUseCase {

    Customer createCustomer(Customer customer);

    Optional<Customer> getCustomerById(Long id);

    List<Customer> getAllCustomers();

    void deleteCustomer(Long id);
}

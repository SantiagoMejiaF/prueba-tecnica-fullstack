package com.litethinking.infrastructure.ports.out;

import com.litethinking.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerPort {

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    void deleteById(Long id);

    List<Customer> findAll();
}

package com.litethinking.infrastructure.ports.out;

import com.litethinking.domain.Customer;

import java.util.Optional;

public interface CustomerPort {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByEmail(String email);

    void deleteById(Long id);
}

package com.litethinking.infrastructure.adapters.in.rest.controllers;

import com.litethinking.domain.Customer;
import com.litethinking.infrastructure.adapters.in.rest.controllers.mappers.CustomerMapper;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.CustomerRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.CustomerResponse;
import com.litethinking.infrastructure.ports.in.CustomerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.customer}")
public class CustomerController {

    private final CustomerUseCase customerUseCase;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = customerMapper.toDomain(customerRequest);
        Customer createdCustomer = customerUseCase.createCustomer(customer);
        return new ResponseEntity<>(customerMapper.toResponse(createdCustomer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerUseCase.getCustomerById(id);
        return customer.map(value -> ResponseEntity.ok(customerMapper.toResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<Customer> customers = customerUseCase.getAllCustomers();
        return ResponseEntity.ok(customerMapper.toResponses(customers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerUseCase.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

package com.litethinking.infrastructure.adapters.out.database;

import com.litethinking.domain.Customer;
import com.litethinking.infrastructure.adapters.out.database.entities.CustomerEntity;
import com.litethinking.infrastructure.adapters.out.database.mappers.CustomerOutMapper;
import com.litethinking.infrastructure.adapters.out.database.repository.CustomerRepository;
import com.litethinking.infrastructure.ports.out.CustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerAdapter implements CustomerPort {

    private final CustomerRepository customerRepository;
    private final CustomerOutMapper customerOutMapper;

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id).map(customerOutMapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity savedEntity = customerRepository.save(customerOutMapper.toEntity(customer));
        return customerOutMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerOutMapper.toDomains(customerRepository.findAll());
    }
}

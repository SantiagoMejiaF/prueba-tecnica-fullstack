package com.litethinking.application.usecases;

import com.litethinking.domain.Customer;
import com.litethinking.infrastructure.ports.in.CustomerUseCase;
import com.litethinking.infrastructure.ports.out.CustomerPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerPort customerPort;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerPort.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerPort.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerPort.findAll();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerPort.deleteById(id);
    }
}

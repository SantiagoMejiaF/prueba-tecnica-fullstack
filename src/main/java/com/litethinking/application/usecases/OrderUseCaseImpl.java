package com.litethinking.application.usecases;

import com.litethinking.domain.Order;
import com.litethinking.domain.Customer;
import com.litethinking.domain.Product;
import com.litethinking.infrastructure.ports.in.OrderUseCase;
import com.litethinking.infrastructure.ports.out.OrderPort;
import com.litethinking.infrastructure.ports.out.CustomerPort;
import com.litethinking.infrastructure.ports.out.ProductPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderPort orderPort;
    private final CustomerPort customerPort;
    private final ProductPort productPort;

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderPort.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderPort.findAll();
    }

    @Override
    public Order createOrder(Order order, Long customerId, List<Long> productIds) {

        Customer customer = customerPort.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID: " + customerId + " no encontrado"));

        List<Product> products = productPort.findAllById(productIds);
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron productos con los IDs proporcionados");
        }

        order.setCustomer(customer);
        order.setProducts(products);

        return orderPort.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order, Long customerId, List<Long> productIds) {

        Optional<Order> existingOrder = orderPort.findById(id);
        if (existingOrder.isEmpty()) {
            throw new EntityNotFoundException("Orden con ID: " + id + " no encontrada");
        }

        Customer customer = customerPort.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID: " + customerId + " no encontrado"));

        List<Product> products = productPort.findAllById(productIds);
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron productos con los IDs proporcionados");
        }

        order.setId(id);
        order.setCustomer(customer);
        order.setProducts(products);

        return orderPort.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderPort.deleteById(id);
    }
}

package com.litethinking.infrastructure.ports.in;

import com.litethinking.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderUseCase {

    Optional<Order> getOrderById(Long id);

    List<Order> getAllOrders();

    Order createOrder(Order order, Long customerId, List<Long> productIds);

    Order updateOrder(Long id, Order order, Long customerId, List<Long> productIds);

    void deleteOrder(Long id);
}


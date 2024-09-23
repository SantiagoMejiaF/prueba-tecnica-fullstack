package com.litethinking.infrastructure.ports.out;

import com.litethinking.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderPort {

    Order save(Order order);

    Optional<Order> findById(Long id);

    void deleteById(Long id);

    List<Order> findAll();
}



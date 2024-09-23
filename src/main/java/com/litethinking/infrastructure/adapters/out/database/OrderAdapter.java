package com.litethinking.infrastructure.adapters.out.database;

import com.litethinking.domain.Order;
import com.litethinking.infrastructure.adapters.out.database.entities.OrderEntity;
import com.litethinking.infrastructure.adapters.out.database.mappers.OrderOutMapper;
import com.litethinking.infrastructure.adapters.out.database.repository.OrderRepository;
import com.litethinking.infrastructure.ports.out.OrderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderAdapter implements OrderPort {

    private final OrderRepository orderRepository;
    private final OrderOutMapper orderOutMapper;

    @Override
    public Order save(Order order) {
        OrderEntity savedEntity = orderRepository.save(orderOutMapper.toEntity(order));
        return orderOutMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id).map(orderOutMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderOutMapper.toDomains(orderRepository.findAll());
    }
}


package com.litethinking.infrastructure.adapters.in.rest.controllers;

import com.litethinking.domain.Order;
import com.litethinking.infrastructure.adapters.in.rest.config.OrderAPI;
import com.litethinking.infrastructure.adapters.in.rest.controllers.mappers.OrderMapper;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.OrderRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.OrderResponse;
import com.litethinking.infrastructure.ports.in.OrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("${request-mapping.controller.order}")
public class OrderController implements OrderAPI {

    private final OrderUseCase orderUseCase;
    private final OrderMapper orderMapper;

    @Override
    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
        Long customerId = orderRequest.getCustomerId();
        List<Long> productIds = orderRequest.getProductIds();
        Order order = orderMapper.toDomain(orderRequest);
        Order createdOrder = orderUseCase.createOrder(order, customerId, productIds);
        return new ResponseEntity<>(orderMapper.toResponse(createdOrder), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderResponse> updateOrder(Long id, OrderRequest orderRequest) {
        Order order = orderMapper.toDomain(orderRequest);
        Long customerId = orderRequest.getCustomerId();
        List<Long> productIds = orderRequest.getProductIds();
        Order updatedOrder = orderUseCase.updateOrder(id, order, customerId, productIds);
        return ResponseEntity.ok(orderMapper.toResponse(updatedOrder));
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long id) {
        orderUseCase.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<OrderResponse> getOrder(Long id) {
        Optional<Order> order = orderUseCase.getOrderById(id);
        return order.map(o -> ResponseEntity.ok(orderMapper.toResponse(o)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listOrders() {
        List<Order> orders = orderUseCase.getAllOrders();
        List<OrderResponse> orderResponses = orders.stream()
                .map(orderMapper::toResponse)
                .toList();
        return ResponseEntity.ok(orderResponses);
    }
}

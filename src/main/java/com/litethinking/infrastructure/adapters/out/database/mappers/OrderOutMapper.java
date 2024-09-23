package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Order;
import com.litethinking.infrastructure.adapters.out.database.entities.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderOutMapper {

    Order toDomain(OrderEntity orderEntity);

    List<Order> toDomains(List<OrderEntity> orderEntities);

    OrderEntity toEntity(Order order);
}

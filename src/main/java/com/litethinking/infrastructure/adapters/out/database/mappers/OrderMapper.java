package com.litethinking.infrastructure.adapters.out.database.mappers;

import com.litethinking.domain.Order;
import com.litethinking.infrastructure.adapters.out.database.entities.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toDomain (OrderEntity orderEntity);

    OrderEntity toEntity (Order order);
}

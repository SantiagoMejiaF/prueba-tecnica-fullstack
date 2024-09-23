package com.litethinking.infrastructure.adapters.in.rest.controllers.mappers;

import com.litethinking.domain.Order;
import com.litethinking.infrastructure.adapters.in.rest.controllers.requests.OrderRequest;
import com.litethinking.infrastructure.adapters.in.rest.controllers.responses.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toDomain(OrderRequest orderRequest);

    OrderResponse toResponse(Order order);
}

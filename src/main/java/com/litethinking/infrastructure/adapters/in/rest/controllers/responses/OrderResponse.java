package com.litethinking.infrastructure.adapters.in.rest.controllers.responses;

import com.litethinking.domain.Customer;
import com.litethinking.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private Long id;

    private Customer customer;

    private List<Product> products;

    private Double total;
}

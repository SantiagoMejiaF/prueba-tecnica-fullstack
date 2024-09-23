package com.litethinking.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Customer customer;

    private List<Product> products;

    private Double total;
}

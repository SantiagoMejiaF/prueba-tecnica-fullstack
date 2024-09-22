package com.litethinking.domain;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Customer customer;

    private Set<Product> products;

    private Double total;
}

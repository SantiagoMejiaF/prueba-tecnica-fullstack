package com.litethinking.domain;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long code;

    private String name;

    private String features;

    private Double price;

    private Company company;

    private Set<Category> categories;
}

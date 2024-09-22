package com.litethinking.domain;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private String nit;

    private String name;

    private String address;

    private String phone;

    private Set<Product> products;
}

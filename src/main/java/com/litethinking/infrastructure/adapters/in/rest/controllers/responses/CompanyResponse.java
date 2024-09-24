package com.litethinking.infrastructure.adapters.in.rest.controllers.responses;

import com.litethinking.domain.Product;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {

    private String nit;

    private String name;

    private String address;

    private String phone;

    private Set<Product> products;
}

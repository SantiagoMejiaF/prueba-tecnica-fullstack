package com.litethinking.infrastructure.adapters.in.rest.controllers.responses;

import com.litethinking.domain.Category;
import com.litethinking.domain.Company;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {

    private Long code;

    private String name;

    private String features;

    private Double price;

    private Company company;

    private List<Category> categories;
}

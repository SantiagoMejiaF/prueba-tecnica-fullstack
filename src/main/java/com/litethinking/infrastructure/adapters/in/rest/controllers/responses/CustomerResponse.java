package com.litethinking.infrastructure.adapters.in.rest.controllers.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private Long id;
    private String name;
    private String email;
}

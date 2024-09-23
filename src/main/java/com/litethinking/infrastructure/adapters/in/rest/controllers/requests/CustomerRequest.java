package com.litethinking.infrastructure.adapters.in.rest.controllers.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private String name;
    private String email;
}

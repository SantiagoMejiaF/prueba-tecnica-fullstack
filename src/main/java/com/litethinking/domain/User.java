package com.litethinking.domain;

import com.litethinking.domain.enums.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private String password;

    private UserRole role;
}

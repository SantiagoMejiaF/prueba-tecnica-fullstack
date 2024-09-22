package com.litethinking.infrastructure.ports.out;

import com.litethinking.domain.User;
import java.util.Optional;

public interface UserPort {

    Optional<User> findByEmail(String email);

    User save(User user);
}

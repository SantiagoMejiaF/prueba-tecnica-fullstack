package com.litethinking.infrastructure.adapters.out.database.repository;

import com.litethinking.domain.User;
import com.litethinking.infrastructure.adapters.out.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<User> findByEmail(String email);
}

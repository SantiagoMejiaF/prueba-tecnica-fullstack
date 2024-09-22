package com.litethinking.infrastructure.adapters.out.database.repository;

import com.litethinking.infrastructure.adapters.out.database.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> { }

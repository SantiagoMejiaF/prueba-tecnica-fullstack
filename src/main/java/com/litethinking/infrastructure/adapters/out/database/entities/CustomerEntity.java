package com.litethinking.infrastructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "SEQ_CUSTOMER", allocationSize = 1)
@Table(name = "CLIENTE")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @Column(name = "ID", nullable = false)
    @Comment("Identificador único del cliente")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    @Comment("Nombre del cliente")
    private String name;

    @Column(name = "CORREO", length = 100, nullable = false)
    @Comment("Correo electrónico del cliente")
    private String email;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    @Comment("Órdenes asociadas al cliente")
    private Set<OrderEntity> orderEntities;
}

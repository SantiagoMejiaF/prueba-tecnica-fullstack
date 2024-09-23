package com.litethinking.infrastructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "ORDER_SEQ", sequenceName = "SEQ_ORDER", allocationSize = 1)
@Table(name = "ORDEN")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @Column(name = "ID", nullable = false)
    @Comment("Identificador único de la orden")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    @Comment("Cliente que realizó la orden")
    private CustomerEntity customer;

    @ManyToMany
    @JoinTable(
            name = "ORDEN_PRODUCTO",
            joinColumns = @JoinColumn(name = "ORDEN_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCTO_ID")
    )
    @Comment("Productos en la orden")
    private List<ProductEntity> products;

    @Column(name = "TOTAL", nullable = false)
    @Comment("Total de la orden")
    private Double total;
}

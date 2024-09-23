package com.litethinking.infrastructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "PRODUCTO")
public class ProductEntity {

    @Id
    @Column(name = "CODIGO", nullable = false)
    @Comment("Código único del producto")
    private Long code;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    @Comment("Nombre del producto")
    private String name;

    @Column(name = "CARACTERISTICAS", length = 100)
    @Comment("Características del producto")
    private String features;

    @Column(name = "PRECIO", nullable = false)
    @Comment("Precio del producto")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "NIT_EMPRESA", nullable = false)
    @Comment("Empresa que vende el producto")
    private CompanyEntity company;

    @ManyToMany
    @JoinTable(
            name = "PRODUCTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "PRODUCTO_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID")
    )
    @Comment("Categorías del producto")
    private List<CategoryEntity> categories;
}

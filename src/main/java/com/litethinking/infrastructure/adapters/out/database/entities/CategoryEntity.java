package com.litethinking.infrastructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
@Table(name = "CATEGORIA")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    @Column(name = "ID", nullable = false)
    @Comment("Identificador único de la categoría")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    @Comment("Nombre de la categoría")
    private String name;

    @ManyToMany(mappedBy = "categories")
    @Comment("Productos asociados a esta categoría")
    private Set<ProductEntity> productEntities;
}

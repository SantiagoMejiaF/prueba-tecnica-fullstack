package com.litethinking.infrastructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "EMPRESA_SEQ", sequenceName = "SEQ_EMPRESA", allocationSize = 1)
@Table(name = "EMPRESA")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESA_SEQ")
    @Column(name = "NIT", length = 10, nullable = false)
    @Comment("Número de identificación tributaria de la empresa")
    private String nit;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    @Comment("Nombre de la empresa")
    private String name;

    @Column(name = "DIRECCION", length = 50, nullable = false)
    @Comment("Dirección de la empresa")
    private String address;

    @Column(name = "TELEFONO", length = 10, nullable = false)
    @Comment("Teléfono de la empresa")
    private String phone;

    @OneToMany(mappedBy = "companyEntity", cascade = CascadeType.ALL)
    @Comment("Productos de la empresa")
    private Set<ProductEntity> productEntities;
}

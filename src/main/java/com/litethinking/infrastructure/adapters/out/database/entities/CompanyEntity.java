package com.litethinking.infrastructure.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Table(name = "EMPRESA")
public class CompanyEntity {

    @Id
    @Column(name = "NIT", length = 10, unique = true, nullable = false)
    @Comment("Número de identificación tributaria de la empresa")
    private String nit;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    @Comment("Nombre de la empresa")
    private String name;

    @Column(name = "DIRECCION", length = 50, nullable = false)
    @Comment("Dirección de la empresa")
    private String address;

    @Column(name = "TELEFONO", length = 10, unique = true, nullable = false)
    @Comment("Teléfono de la empresa")
    private String phone;
}

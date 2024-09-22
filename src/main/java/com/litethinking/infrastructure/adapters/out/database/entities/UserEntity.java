package com.litethinking.infrastructure.adapters.out.database.entities;

import com.litethinking.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "USER_SEQ", sequenceName = "SEQ_USER", allocationSize = 1)
@Table(name = "USUARIO")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @Column(name = "ID", nullable = false)
    @Comment("Identificador único del usuario")
    private Long id;

    @Column(name = "CORREO", length = 100, nullable = false, unique = true)
    @Comment("Correo electrónico del usuario")
    private String email;

    @Column(name = "CONTRASEÑA", nullable = false)
    @Comment("Contraseña encriptada del usuario")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL", nullable = false)
    @Comment("Rol del usuario: Administrador o Externo")
    private UserRole role;
}

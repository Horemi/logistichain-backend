package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"roles"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombreUsuario;

    private String contrasena;
    private String nombreCompleto;

    @Column(unique = true)
    private String email;

    private Boolean activo = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_ur_usuario")),
            inverseJoinColumns = @JoinColumn(name = "rol_id", foreignKey = @ForeignKey(name = "fk_ur_rol"))
    )
    private Set<Rol> roles = new HashSet<>();

    public void addRol(Rol rol) { roles.add(rol); }
    public void removeRol(Rol rol) { roles.remove(rol); }
}

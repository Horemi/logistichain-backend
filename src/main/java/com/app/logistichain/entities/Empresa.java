package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sucursales"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;
    private String identificadorFiscal;
    private String direccionPrincipal;
    private String telefono;
    private String email;
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sucursal> sucursales = new ArrayList<>();

    // ======================================
    // Métodos de conveniencia bidireccionales
    // ======================================
    public void addSucursal(Sucursal sucursal) {
        if (!sucursales.contains(sucursal)) {
            sucursales.add(sucursal);
            sucursal.setEmpresa(this);
        }
    }

    public void removeSucursal(Sucursal sucursal) {
        if (sucursales.remove(sucursal)) {
            sucursal.setEmpresa(null);
        }
    }
}

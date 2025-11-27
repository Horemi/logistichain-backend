package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "proveedores")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"ordenes"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;
    private String identificadorFiscal;
    private String direccion;
    private String telefono;
    private String emailContacto;

    // Proveedor 1–N OrdenCompra
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenCompra> ordenes = new ArrayList<>();

    public void addOrden(OrdenCompra oc) {
        ordenes.add(oc);
        oc.setProveedor(this);
    }

    public void removeOrden(OrdenCompra oc) {
        ordenes.remove(oc);
        oc.setProveedor(null);
    }
}

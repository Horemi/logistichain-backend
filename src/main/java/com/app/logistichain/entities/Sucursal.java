package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "sucursales")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"almacenes"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", foreignKey = @ForeignKey(name = "fk_sucursal_empresa"))
    private Empresa empresa;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Almacen> almacenes = new ArrayList<>();

    // Helpers para mantener ambos lados
    public void addAlmacen(Almacen almacen) {
        if (!almacenes.contains(almacen)) {
            almacenes.add(almacen);
            almacen.setSucursal(this);
        }
    }

    public void removeAlmacen(Almacen almacen) {
        if (almacenes.remove(almacen)) {
            almacen.setSucursal(null);
        }
    }
}

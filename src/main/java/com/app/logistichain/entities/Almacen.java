package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "almacenes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"ubicaciones"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;
    private String tipo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", foreignKey = @ForeignKey(name = "fk_almacen_sucursal"))
    private Sucursal sucursal;

    @OneToMany(mappedBy = "almacen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ubicacion> ubicaciones = new ArrayList<>();

    public void addUbicacion(Ubicacion ubicacion) {
        ubicaciones.add(ubicacion);
        ubicacion.setAlmacen(this);
    }

    public void removeUbicacion(Ubicacion ubicacion) {
        ubicaciones.remove(ubicacion);
        ubicacion.setAlmacen(null);
    }
}

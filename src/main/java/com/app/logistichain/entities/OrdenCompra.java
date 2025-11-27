package com.app.logistichain.entities;

import com.app.logistichain.entities.enums.EstadoOrdenCompra;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "ordenes_compra")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"proveedor", "detalles"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", foreignKey = @ForeignKey(name = "fk_oc_proveedor"))
    private Proveedor proveedor;

    private LocalDate fechaEmision;
    private LocalDate fechaEntregaEsperada;

    @Enumerated(EnumType.STRING)
    private EstadoOrdenCompra estado;

    private BigDecimal costoTotal;
    private Long usuarioCreadorId;

    // OrdenCompra 1–N DetalleOC
    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOC> detalles = new ArrayList<>();

    // Recepciones asociadas a esta OC (no siempre, puede haber recepciones sin OC)
    @OneToMany(mappedBy = "ordenCompra")
    private List<Recepcion> recepciones = new ArrayList<>();

    public void addDetalle(DetalleOC d) {
        detalles.add(d);
        d.setOrdenCompra(this);
    }

    public void removeDetalle(DetalleOC d) {
        detalles.remove(d);
        d.setOrdenCompra(null);
    }
}

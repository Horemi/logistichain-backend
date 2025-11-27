package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "productos",
        indexes = {
                @Index(name = "ix_producto_sku", columnList = "sku", unique = true),
                @Index(name = "ix_producto_gtin", columnList = "gtin")
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"lotes", "detallesCompra", "detallesVenta", "movimientosKardex"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String sku;
    private String gtin;
    private String descripcion;
    private String marca;
    private String unidadMedida;

    private BigDecimal costoPromedio;
    private BigDecimal precioVentaBase;
    private Double pesoKg;

    private Boolean manejaLote;
    private Boolean manejaFechaVencimiento;

    private Integer stockMinimo;
    private Integer stockMaximo;

    // ======================
    // Relaciones
    // ======================

    // Producto 1–N Lote
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lote> lotes = new ArrayList<>();

    // N–M vía detalle (lado inverso; el dueño es DetalleOC.producto)
    @OneToMany(mappedBy = "producto")
    private List<DetalleOC> detallesCompra = new ArrayList<>();

    // N–M vía detalle (lado inverso; el dueño es DetallePV.producto)
    @OneToMany(mappedBy = "producto")
    private List<DetallePV> detallesVenta = new ArrayList<>();

    // Movimientos de inventario
    @OneToMany(mappedBy = "producto")
    private List<Kardex> movimientosKardex = new ArrayList<>();

    // ======================
    // Conveniencia (bidireccional)
    // ======================
    public void addLote(Lote lote) {
        lotes.add(lote);
        lote.setProducto(this);
    }

    public void removeLote(Lote lote) {
        lotes.remove(lote);
        lote.setProducto(null);
    }
}

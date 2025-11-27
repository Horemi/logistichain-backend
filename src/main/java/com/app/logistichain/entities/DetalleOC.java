package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_oc")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"ordenCompra", "producto"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleOC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_compra_id", foreignKey = @ForeignKey(name = "fk_doc_oc"))
    private OrdenCompra ordenCompra;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "fk_doc_producto"))
    private Producto producto;

    private Integer cantidad;
    private BigDecimal costoUnitario;
}

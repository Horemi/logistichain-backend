package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_pv")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"pedido", "producto"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetallePV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_venta_id", foreignKey = @ForeignKey(name = "fk_dpv_pedido"))
    private PedidoVenta pedido;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "fk_dpv_producto"))
    private Producto producto;

    private Integer cantidad;
    private BigDecimal precioUnitarioVenta;
}

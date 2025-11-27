package com.app.logistichain.entities;

import com.app.logistichain.entities.enums.EstadoPedido;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "pedidos_venta")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"cliente", "detalles"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PedidoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_pv_cliente"))
    private Cliente cliente;

    private LocalDate fechaEmision;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    private BigDecimal precioTotal;
    private String direccionDespacho;
    private Long vendedorId;

    // PedidoVenta 1–N DetallePV
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePV> detalles = new ArrayList<>();

    // Un pedido puede generar uno o más pickings (depende de tu flujo)
    @OneToMany(mappedBy = "pedido")
    private List<Picking> pickings = new ArrayList<>();

    public void addDetalle(DetallePV d) {
        detalles.add(d);
        d.setPedido(this);
    }

    public void removeDetalle(DetallePV d) {
        detalles.remove(d);
        d.setPedido(null);
    }
}

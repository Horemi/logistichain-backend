package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items_picking")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"picking", "detallePv", "producto", "lote", "ubicacion"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPicking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "picking_id", foreignKey = @ForeignKey(name = "fk_item_pick_picking"))
    private Picking picking;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "detalle_pv_id", foreignKey = @ForeignKey(name = "fk_item_pick_detallepv"))
    private DetallePV detallePv;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "fk_item_pick_producto"))
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", foreignKey = @ForeignKey(name = "fk_item_pick_lote"))
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id", foreignKey = @ForeignKey(name = "fk_item_pick_ubicacion"))
    private Ubicacion ubicacion;

    private Integer cantidadARecoger;
    private Integer cantidadRecogida;
}

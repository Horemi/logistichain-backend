package com.app.logistichain.entities;

import com.app.logistichain.entities.enums.EstadoPicking;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "pickings")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"pedido", "items"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Picking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_venta_id", foreignKey = @ForeignKey(name = "fk_pick_pedido"))
    private PedidoVenta pedido;

    private Instant fechaAsignacion;

    @Enumerated(EnumType.STRING)
    private EstadoPicking estado;

    private Long pickerAsignadoId; // Usuario picker (FK cruda)

    @OneToMany(mappedBy = "picking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPicking> items = new ArrayList<>();

    public void addItem(ItemPicking it) {
        items.add(it);
        it.setPicking(this);
    }

    public void removeItem(ItemPicking it) {
        items.remove(it);
        it.setPicking(null);
    }
}

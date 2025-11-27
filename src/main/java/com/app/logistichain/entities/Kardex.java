package com.app.logistichain.entities;

import com.app.logistichain.entities.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "kardex")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"producto", "lote", "almacen"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Kardex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Instant fecha;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "fk_kardex_producto"))
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", foreignKey = @ForeignKey(name = "fk_kardex_lote"))
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "almacen_id", foreignKey = @ForeignKey(name = "fk_kardex_almacen"))
    private Almacen almacen;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    private Integer cantidad;         // +entrada / -salida
    private Integer saldoResultante;  // stock final
    private String referenciaDocumento;
}

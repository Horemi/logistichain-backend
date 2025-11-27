package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "lotes",
        indexes = @Index(name = "ix_lote_codigo", columnList = "codigoLote"))
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"producto"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "fk_lote_producto"))
    private Producto producto;

    private String codigoLote;
    private LocalDate fechaVencimiento; // opcional
    private LocalDate fechaRecepcion;
    private Integer stockActual;
}

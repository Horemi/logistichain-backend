package com.app.logistichain.entities;

import com.app.logistichain.entities.enums.EstadoRecepcion;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "recepciones")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"ordenCompra"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Recepcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // puede ser null si recepción sin OC
    @JoinColumn(name = "orden_compra_id", foreignKey = @ForeignKey(name = "fk_recepcion_oc"))
    private OrdenCompra ordenCompra;

    private Instant fechaRecepcion;
    private String guiaDespachoProveedor;
    private Long usuarioReceptorId;

    @Enumerated(EnumType.STRING)
    private EstadoRecepcion estado;
}

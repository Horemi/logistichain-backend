package com.app.logistichain.dtos;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KardexDto {
    private Long id;
    private Instant fecha;
    private String tipoMovimiento;
    private Integer cantidad;
    private Integer saldoResultante;
    private String referenciaDocumento;

    // 🔹 Relaciones
    private Long productoId;
    private String productoDescripcion;

    private Long loteId;
    private String loteCodigo;

    private Long almacenId;
    private String almacenNombre;
}

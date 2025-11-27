package com.app.logistichain.dtos;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompraDto {
    private Long id;
    private LocalDate fechaEmision;
    private LocalDate fechaEntregaEsperada;
    private String estado;
    private BigDecimal costoTotal;
    private Long usuarioCreadorId;

    // 🔹 Relación con Proveedor
    private Long proveedorId;
    private String proveedorNombre;
}

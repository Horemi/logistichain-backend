package com.app.logistichain.dtos;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOCDto {
    private Long id;
    private Integer cantidad;
    private BigDecimal costoUnitario;

    // 🔹 Relaciones
    private Long ordenCompraId;
    private Long productoId;

    private String productoDescripcion;
}

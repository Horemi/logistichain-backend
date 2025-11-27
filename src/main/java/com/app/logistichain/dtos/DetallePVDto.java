package com.app.logistichain.dtos;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePVDto {
    private Long id;
    private Integer cantidad;
    private BigDecimal precioUnitarioVenta;

    // 🔹 Relaciones
    private Long pedidoVentaId;
    private Long productoId;

    private String productoDescripcion;
}

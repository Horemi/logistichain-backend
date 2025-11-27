package com.app.logistichain.dtos;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PickingDto {
    private Long id;
    private Instant fechaAsignacion;
    private String estado;
    private Long pickerAsignadoId;

    // 🔹 Relación con PedidoVenta
    private Long pedidoVentaId;
}

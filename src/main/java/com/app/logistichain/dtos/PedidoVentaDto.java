package com.app.logistichain.dtos;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoVentaDto {
    private Long id;
    private LocalDate fechaEmision;
    private String estado;
    private BigDecimal precioTotal;
    private String direccionDespacho;
    private Long vendedorId;

    // 🔹 Relación con Cliente
    private Long clienteId;
    private String clienteNombre;
}

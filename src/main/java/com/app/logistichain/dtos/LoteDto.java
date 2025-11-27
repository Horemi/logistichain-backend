package com.app.logistichain.dtos;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoteDto {
    private Long id;
    private String codigoLote;
    private LocalDate fechaVencimiento;
    private LocalDate fechaRecepcion;
    private Integer stockActual;

    // 🔹 Relación con Producto
    private Long productoId;
    private String productoDescripcion;
}

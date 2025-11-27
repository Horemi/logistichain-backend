package com.app.logistichain.dtos;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionDto {
    private Long id;
    private Instant fechaRecepcion;
    private String guiaDespachoProveedor;
    private Long usuarioReceptorId;
    private String estado;

    // 🔹 Relación con OrdenCompra
    private Long ordenCompraId;
}

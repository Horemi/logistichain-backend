package com.app.logistichain.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPickingDto {
    private Long id;
    private Integer cantidadARecoger;
    private Integer cantidadRecogida;

    // 🔹 Relaciones
    private Long pickingId;
    private Long detallePvId;
    private Long productoId;
    private String productoDescripcion;
    private Long loteId;
    private String loteCodigo;
    private Long ubicacionId;
    private String ubicacionCodigo;
}

package com.app.logistichain.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlmacenDto {

    private Long id;
    private String nombre;
    private String tipo;

    // 🔹 Relación con Sucursal
    private Long sucursalId;
    private String sucursalNombre;
}

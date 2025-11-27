package com.app.logistichain.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDto {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;

    // 🔹 Relación con Empresa
    private Long empresaId;
    private String empresaNombre;
}

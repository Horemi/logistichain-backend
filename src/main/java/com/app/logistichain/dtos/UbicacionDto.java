package com.app.logistichain.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionDto {

    private Long id;
    private String codigo;
    private String pasillo;
    private String estante;
    private String nivel;

    // 🔹 Relación con Almacén
    private Long almacenId;
    private String almacenNombre;
}

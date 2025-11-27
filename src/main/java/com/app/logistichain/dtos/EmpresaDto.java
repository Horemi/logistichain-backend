package com.app.logistichain.dtos;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDto {

    private Long id;
    private String nombre;
    private String identificadorFiscal;
    private String direccionPrincipal;
    private String telefono;
    private String email;
    private Instant fechaCreacion;

    // 🔹 No incluimos lista de sucursales para evitar ciclos
}

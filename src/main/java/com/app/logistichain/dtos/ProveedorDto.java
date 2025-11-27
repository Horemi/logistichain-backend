package com.app.logistichain.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDto {
    private Long id;
    private String nombre;
    private String identificadorFiscal;
    private String direccion;
    private String telefono;
    private String emailContacto;
}

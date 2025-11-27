package com.app.logistichain.dtos;

import lombok.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    private String email;
    private Boolean activo;

    // 🔹 Roles asignados
    private List<Long> rolIds = new ArrayList<>();
    private List<String> rolNombres = new ArrayList<>();
}

package com.app.logistichain.dtos;
import lombok.Getter;
import lombok.Setter;

import java.util.List;  // <--- AGREGA ESTA LÍNEA


@Getter
@Setter
public class LoginResponse {
    private String token;
    private String nombreUsuario;
    private List<String> roles;
}

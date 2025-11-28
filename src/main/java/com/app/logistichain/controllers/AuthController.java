package com.app.logistichain.controllers;

import com.app.logistichain.dtos.LoginRequest;
import com.app.logistichain.dtos.LoginResponse;
import com.app.logistichain.entities.Usuario;
import com.app.logistichain.repositories.UsuarioRepository;
import com.app.logistichain.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList; // Importante
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        // 1. Buscar el usuario SIN lanzar excepción
        Usuario usuario = usuarioRepository.findByNombreUsuario(loginRequest.getNombreUsuario())
                .orElse(null);

        // 2. Si no existe o la contraseña es incorrecta → 401, NO 500
        if (usuario == null || !usuario.getContrasena().equals(loginRequest.getContrasena())) {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        }

        // 3. Generar el token JWT
        String token = jwtService.generateToken(usuario);

        // 4. Conversión segura de roles
        List<String> rolesStr = new ArrayList<>();
        if (usuario.getRoles() != null) {
            rolesStr = usuario.getRoles().stream()
                    .map(rol -> rol.getNombre().toString())
                    .collect(Collectors.toList());
        }

        // 5. Crear la respuesta
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setNombreUsuario(usuario.getNombreUsuario());
        response.setRoles(rolesStr);

        return ResponseEntity.ok(response);
    }

}
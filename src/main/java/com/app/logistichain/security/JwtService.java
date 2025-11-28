package com.app.logistichain.security;

import com.app.logistichain.entities.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys; // Importante: Nuevo import
import org.springframework.stereotype.Service;

import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // Esta clave debe ser larga (mínimo 32 caracteres) para que HS256 no falle
    private final String SECRET_KEY = "mi_clave_secreta_que_debe_ser_muy_larga_y_segura_12345";
    private final long EXPIRATION_TIME = 86400000;  // 1 día

    public String generateToken(Usuario usuario) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(usuario.getNombreUsuario())
                .setIssuedAt(now)
                .setExpiration(expiration)
                // CORRECCIÓN: Usamos el método getSigningKey()
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Método auxiliar obligatorio en versiones nuevas de JJWT
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
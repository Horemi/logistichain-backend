package com.app.logistichain.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF (necesario para JWT)
                .authorizeHttpRequests(auth -> auth
                        // Permitimos la ruta del login tal cual está en el AuthController
                        .requestMatchers("/auth/**").permitAll()

                        // Mantenemos /api/auth/** por seguridad
                        .requestMatchers("/api/auth/**").permitAll()

                        // Permitimos el registro de usuarios
                        .requestMatchers("/api/usuarios/**").permitAll()

                        // Permitimos Swagger para que puedas probar
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Todo lo demás requiere token
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
// Actualización forzada de seguridad para Railway
package com.example.searchservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas o API pública
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/search/**").permitAll() // Permitir acceso público a los endpoints de búsqueda
                .anyRequest().authenticated() // Requiere autenticación para otros endpoints (si existen)
            )
            .httpBasic(); // Autenticación básica si es requerida en otros endpoints
        return http.build();
    }
}

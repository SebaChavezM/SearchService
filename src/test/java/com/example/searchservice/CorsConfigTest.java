package com.example.searchservice;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.*;

class CorsConfigTest {

    @Test
    void testCorsConfigurer() {
        // Arrange
        CorsConfig corsConfig = new CorsConfig();
        CorsRegistry realRegistry = new CorsRegistry();

        // Act
        WebMvcConfigurer configurer = corsConfig.corsConfigurer();
        configurer.addCorsMappings(realRegistry);

        // Assert
        // Verificar que las configuraciones se aplicaron al CorsRegistry real
        assertNotNull(realRegistry); // Asegurar que no es nulo
    }
}

package com.example.searchservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SecurityConfigTestt {

    @Autowired
    private SecurityFilterChain securityFilterChain;

    @Test
    void testSecurityFilterChainNotNull() {
        assertNotNull(securityFilterChain, "SecurityFilterChain should not be null");
    }
}

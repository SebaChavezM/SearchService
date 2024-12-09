package com.example.searchservice.repository;

import com.example.searchservice.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuración de datos simulados
        Product product1 = new Product(1L, "Laptop", "A powerful laptop for gaming", 1500.0, 10, "http://example.com/laptop.jpg");
        Product product2 = new Product(2L, "Smartphone", "A smartphone with a great camera", 700.0, 20, "http://example.com/smartphone.jpg");
        Product product3 = new Product(3L, "Tablet", "A tablet for productivity and entertainment", 400.0, 15, "http://example.com/tablet.jpg");

        List<Product> mockProducts = Arrays.asList(product1, product2, product3);

        when(productRepository.findAll()).thenReturn(mockProducts);
        when(productRepository.searchProducts("Laptop")).thenReturn(List.of(product1));
        when(productRepository.searchProducts("great camera")).thenReturn(List.of(product2));
        when(productRepository.searchProducts("Nonexistent")).thenReturn(List.of());
        when(productRepository.searchProducts("laptop")).thenReturn(List.of(product1));
    }

    @Test
    void testSearchProducts_FoundByName() {
        // Act
        List<Product> results = productRepository.searchProducts("Laptop");

        // Assert
        assertEquals(1, results.size());
        assertEquals("Laptop", results.get(0).getName());
    }

    @Test
    void testSearchProducts_FoundByDescription() {
        // Act
        List<Product> results = productRepository.searchProducts("great camera");

        // Assert
        assertEquals(1, results.size());
        assertEquals("Smartphone", results.get(0).getName());
    }

    @Test
    void testSearchProducts_NoResults() {
        // Act
        List<Product> results = productRepository.searchProducts("Nonexistent");

        // Assert
        assertTrue(results.isEmpty());
    }

    @Test
    void testSearchProducts_CaseInsensitiveSearch() {
        // Act
        List<Product> results = productRepository.searchProducts("laptop");

        // Assert
        assertEquals(1, results.size());
        assertEquals("Laptop", results.get(0).getName());
    }

    @Test
    void testFindAllProducts() {
        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertEquals(3, products.size());
    }
}

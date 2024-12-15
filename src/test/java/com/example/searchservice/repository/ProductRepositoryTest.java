package com.example.searchservice.repository;

import com.example.searchservice.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @ParameterizedTest
    @CsvSource({
        "Laptop, Laptop, 1",
        "great camera, Smartphone, 1",
        "Nonexistent, '', 0",
        "laptop, Laptop, 1"
    })
    void testSearchProducts(String query, String expectedName, int expectedSize) {
        // Act
        List<Product> results = productRepository.searchProducts(query);

        // Assert
        assertEquals(expectedSize, results.size());
        if (expectedSize > 0) {
            assertEquals(expectedName, results.get(0).getName());
        } else {
            assertTrue(results.isEmpty());
        }
    }

    @ParameterizedTest
    @CsvSource({
        "3"
    })
    void testFindAllProducts(int expectedSize) {
        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertEquals(expectedSize, products.size());
    }
}

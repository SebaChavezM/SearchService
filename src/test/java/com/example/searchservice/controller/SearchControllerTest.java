package com.example.searchservice.controller;

import com.example.searchservice.entity.Product;
import com.example.searchservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SearchControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private SearchController searchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Product A", "Description A", 100.0, 10, "imageA"),
                new Product(2L, "Product B", "Description B", 200.0, 20, "imageB")
        );
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // Act
        List<Product> products = searchController.getAllProducts();

        // Assert
        assertEquals(2, products.size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById_ProductExists() {
        // Arrange
        Product mockProduct = new Product(1L, "Product A", "Description A", 100.0, 10, "imageA");
        when(productService.getProductById(1L)).thenReturn(Optional.of(mockProduct));

        // Act
        ResponseEntity<Product> response = searchController.getProductById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product A", response.getBody().getName());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testGetProductById_ProductDoesNotExist() {
        // Arrange
        when(productService.getProductById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = searchController.getProductById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testSearchProducts() {
        // Arrange
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Product A", "Description A", 100.0, 10, "imageA")
        );
        when(productService.searchProducts("query")).thenReturn(mockProducts);

        // Act
        List<Product> products = searchController.searchProducts("query");

        // Assert
        assertEquals(1, products.size());
        assertEquals("Product A", products.get(0).getName());
        verify(productService, times(1)).searchProducts("query");
    }
}

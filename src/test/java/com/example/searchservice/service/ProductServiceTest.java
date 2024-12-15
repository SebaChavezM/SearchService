package com.example.searchservice.service;

import com.example.searchservice.entity.Product;
import com.example.searchservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

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
        when(productRepository.findAll()).thenReturn(mockProducts);

        // Act
        List<Product> products = productService.getAllProducts();

        // Assert
        assertEquals(2, products.size());
        assertEquals("Product A", products.get(0).getName());
        assertEquals("Product B", products.get(1).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById_ProductExists() {
        // Arrange
        Product mockProduct = new Product(1L, "Product A", "Description A", 100.0, 10, "imageA");
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        // Act
        Optional<Product> product = productService.getProductById(1L);

        // Assert
        assertEquals(true, product.isPresent());
        assertEquals("Product A", product.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_ProductDoesNotExist() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Product> product = productService.getProductById(1L);

        // Assert
        assertEquals(false, product.isPresent());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testSearchProducts() {
        // Arrange
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Product A", "Description A", 100.0, 10, "imageA"),
                new Product(2L, "Product B", "Description B", 200.0, 20, "imageB")
        );
        String query = "Product";
        when(productRepository.searchProducts(query)).thenReturn(mockProducts);

        // Act
        List<Product> products = productService.searchProducts(query);

        // Assert
        assertEquals(2, products.size());
        assertEquals("Product A", products.get(0).getName());
        assertEquals("Product B", products.get(1).getName());
        verify(productRepository, times(1)).searchProducts(query);
    }
}

package com.example.searchservice.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductGettersAndSetters() {
        // Arrange
        Long id = 1L;
        String name = "Test Product";
        String description = "Test Description";
        Double price = 99.99;
        Integer stock = 50;
        String imageUrl = "http://example.com/image.jpg";

        // Act
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setImageUrl(imageUrl);

        // Assert
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(stock, product.getStock());
        assertEquals(imageUrl, product.getImageUrl());
    }

    @Test
    void testProductConstructor() {
        // Arrange
        Long id = 1L;
        String name = "Test Product";
        String description = "Test Description";
        Double price = 99.99;
        Integer stock = 50;
        String imageUrl = "http://example.com/image.jpg";

        // Act
        Product product = new Product(id, name, description, price, stock, imageUrl);

        // Assert
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(stock, product.getStock());
        assertEquals(imageUrl, product.getImageUrl());
    }
}

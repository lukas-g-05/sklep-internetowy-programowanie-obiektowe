package com.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Category electronics;
    private Product laptop;

    @BeforeEach
    void setUp() {
        electronics = new Category(1, "Elektronika", "Urządzenia elektroniczne");
        laptop = new Product(1, "Laptop", "Laptop gamingowy", 2999.99, 10, electronics);
    }

    @Test
    void testProductCreation() {
        assertNotNull(laptop);
        assertEquals("Laptop", laptop.getName());
        assertEquals(2999.99, laptop.getPrice(), 0.001);
        assertEquals(10, laptop.getStockQuantity());
        assertEquals(electronics, laptop.getCategory());
    }

    @Test
    void testIsAvailable() {
        assertTrue(laptop.isAvailable());
        
        laptop.setStockQuantity(0);
        assertFalse(laptop.isAvailable());
    }

    @Test
    void testDecreaseStock() {
        laptop.decreaseStock(5);
        assertEquals(5, laptop.getStockQuantity());
    }

    @Test
    void testDecreaseStockInsufficientQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            laptop.decreaseStock(15);
        });
        
        assertEquals("Niewystarczająca ilość produktu w magazynie", exception.getMessage());
    }
}

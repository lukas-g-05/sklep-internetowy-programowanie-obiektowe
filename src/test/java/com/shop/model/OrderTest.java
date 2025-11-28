package com.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Customer customer;
    private Product laptop;
    private Product mouse;
    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        Category electronics = new Category(1, "Elektronika", "Urządzenia elektroniczne");
        customer = new Customer(1, "jan@example.com", "pass123", "Jan", "Kowalski", "Warszawa");
        laptop = new Product(1, "Laptop", "Laptop gamingowy", 2999.99, 10, electronics);
        mouse = new Product(2, "Mysz", "Mysz gamingowa", 199.99, 5, electronics);
        cart = customer.getShoppingCart();
    }

    @Test
    void testOrderCreation() {
        cart.addItem(laptop, 1);
        cart.addItem(mouse, 1);
        
        Order order = cart.checkout();
        
        assertNotNull(order);
        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertEquals(3199.98, order.getTotalAmount(), 0.001);
        assertEquals(2, order.getOrderItems().size());
    }

    @Test
    void testOrderUpdatesStock() {
        cart.addItem(laptop, 2);
        cart.addItem(mouse, 1);
        
        Order order = cart.checkout();
        
        assertEquals(8, laptop.getStockQuantity());
        assertEquals(4, mouse.getStockQuantity());
    }

    @Test
    void testOrderStatusUpdate() {
        cart.addItem(laptop, 1);
        Order order = cart.checkout();
        
        order.updateStatus(OrderStatus.PROCESSING);
        assertEquals(OrderStatus.PROCESSING, order.getStatus());
    }
}

package com.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
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
    void testAddItem() {
        cart.addItem(laptop, 1);
        assertEquals(1, cart.getCartItems().size());
        assertEquals(2999.99, cart.calculateTotal(), 0.001);
    }

    @Test
    void testAddMultipleItems() {
        cart.addItem(laptop, 1);
        cart.addItem(mouse, 2);
        assertEquals(2, cart.getCartItems().size());
        assertEquals(3399.97, cart.calculateTotal(), 0.001);
    }

    @Test
    void testAddDuplicateItem() {
        cart.addItem(laptop, 1);
        cart.addItem(laptop, 2);
        assertEquals(1, cart.getCartItems().size());
        assertEquals(3, cart.getCartItems().get(0).getQuantity());
    }

    @Test
    void testRemoveItem() {
        cart.addItem(laptop, 1);
        cart.addItem(mouse, 1);
        
        CartItem itemToRemove = cart.getCartItems().get(0);
        cart.removeItem(itemToRemove);
        
        assertEquals(1, cart.getCartItems().size());
    }

    @Test
    void testCheckoutEmptyCart() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            cart.checkout();
        });
        
        assertEquals("Koszyk jest pusty", exception.getMessage());
    }
}

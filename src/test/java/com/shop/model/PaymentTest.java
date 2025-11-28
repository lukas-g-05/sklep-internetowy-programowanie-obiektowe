package com.shop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testCreditCardPayment() {
        CreditCardPayment payment = new CreditCardPayment(100.0, "1234567812345678", "12/25", "123");
        
        assertTrue(payment.processPayment());
        assertEquals(PaymentStatus.COMPLETED, payment.getStatus());
        assertEquals(100.0, payment.getAmount(), 0.001);
    }

    @Test
    void testPaymentIntegrationWithOrder() {
        Category electronics = new Category(1, "Elektronika", "Urządzenia elektroniczne");
        Customer customer = new Customer(1, "jan@example.com", "pass123", "Jan", "Kowalski", "Warszawa");
        Product laptop = new Product(1, "Laptop", "Laptop gamingowy", 2999.99, 10, electronics);
        
        ShoppingCart cart = customer.getShoppingCart();
        cart.addItem(laptop, 1);
        Order order = cart.checkout();
        
        CreditCardPayment payment = new CreditCardPayment(order.getTotalAmount(), "1234567812345678", "12/25", "123");
        order.setPayment(payment);
        
        assertEquals(OrderStatus.PAID, order.getStatus());
        assertNotNull(order.getPayment());
    }
}

package com.shop;

import com.shop.model.*;

public class SimpleStoreDemo {
    public static void main(String[] args) {
        System.out.println("=== SYSTEM SKLEPU INTERNETOWEGO - DEMO ===\n");

        // Inicjalizacja repozytorium produktów
        ProductRepository repository = ProductRepository.getInstance();

        // Tworzenie kategorii
        Category electronics = new Category(1, "Elektronika", "Urządzenia elektroniczne");
        Category books = new Category(2, "Książki", "Książki i ebooki");

        // Tworzenie produktów przez administratora
        Admin admin = new Admin(1, "admin@sklep.pl", "admin123", "Anna", "Nowak");
        
        Product laptop = new Product(1, "Laptop Dell", "Laptop do pracy i rozrywki", 3499.99, 5, electronics);
        Product mouse = new Product(2, "Mysz Logitech", "Bezprzewodowa mysz komputerowa", 149.99, 10, electronics);
        Product book = new Product(3, "Java Podstawy", "Książka o programowaniu w Javie", 79.99, 20, books);

        admin.addProduct(laptop, repository);
        admin.addProduct(mouse, repository);
        admin.addProduct(book, repository);

        // Tworzenie klienta
        Customer customer = new Customer(2, "jan@example.com", "haslo123", "Jan", "Kowalski", "Warszawa, ul. Testowa 123");

        System.out.println("1. PRZEGLĄDANIE PRODUKTÓW:");
        System.out.println("Dostępne produkty:");
        for (Product product : repository.getAllProducts()) {
            System.out.println(" - " + product);
        }

        System.out.println("\n2. DODAWANIE PRODUKTÓW DO KOSZYKA:");
        ShoppingCart cart = customer.getShoppingCart();
        cart.addItem(laptop, 1);
        cart.addItem(mouse, 2);
        cart.addItem(book, 1);
        
        System.out.println("Zawartość koszyka:");
        for (CartItem item : cart.getCartItems()) {
            System.out.println(" - " + item);
        }
        System.out.println("Łączna wartość: " + cart.calculateTotal() + " zł");

        System.out.println("\n3. SKŁADANIE ZAMÓWIENIA:");
        Order order = cart.checkout();
        System.out.println("Zamówienie utworzone: " + order);

        System.out.println("\n4. PŁATNOŚĆ:");
        CreditCardPayment payment = new CreditCardPayment(order.getTotalAmount(), "1111222233334444", "12/25", "123");
        order.setPayment(payment);

        System.out.println("\n5. AKTUALIZACJA STATUSU ZAMÓWIENIA:");
        admin.updateOrderStatus(order, OrderStatus.PROCESSING);
        
        System.out.println("\n6. HISTORIA ZAMÓWIEŃ KLIENTA:");
        System.out.println("Historia zamówień Jana Kowalskiego:");
        for (Order customerOrder : customer.getOrderHistory()) {
            System.out.println(" - " + customerOrder);
        }

        System.out.println("\n7. STAN MAGAZYNOWY PO ZAMÓWIENIU:");
        System.out.println("Laptop Dell: " + laptop.getStockQuantity() + " szt.");
        System.out.println("Mysz Logitech: " + mouse.getStockQuantity() + " szt.");
        System.out.println("Java Podstawy: " + book.getStockQuantity() + " szt.");

        System.out.println("\n=== KONIEC DEMONSTRACJI ===");
    }
}

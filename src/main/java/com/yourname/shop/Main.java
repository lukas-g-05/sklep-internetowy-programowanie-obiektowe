package com.yourname.shop;

/**
 * Główna klasa aplikacji sklepu internetowego
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SYSTEM SKLEPU INTERNETOWEGO ===");
        System.out.println("Projekt w trakcie realizacji...");
        
        // Test podstawowej funkcjonalności
        testBasicFunctionality();
    }
    
    private static void testBasicFunctionality() {
        System.out.println("\n--- Test podstawowych klas ---");
        
        // Tutaj będziemy testować nasze klasy
        Product product = new Product("P001", "Laptop", 2999.99);
        System.out.println("Utworzono produkt: " + product.getName());
    }
}

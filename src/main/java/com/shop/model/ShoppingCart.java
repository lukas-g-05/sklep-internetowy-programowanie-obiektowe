package com.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int cartId;
    private Customer customer;
    private List<CartItem> cartItems;
    private static int cartCounter = 0;

    public ShoppingCart(Customer customer) {
        this.cartId = ++cartCounter;
        this.customer = customer;
        this.cartItems = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        if (product.isAvailable() && quantity <= product.getStockQuantity()) {
            // Sprawdź czy produkt już jest w koszyku
            for (CartItem item : cartItems) {
                if (item.getProduct().getProductId() == product.getProductId()) {
                    item.setQuantity(item.getQuantity() + quantity);
                    return;
                }
            }
            // Jeśli nie ma, dodaj nową pozycję
            cartItems.add(new CartItem(product, quantity));
        } else {
            throw new IllegalArgumentException("Produkt niedostępny lub niewystarczająca ilość");
        }
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }

    public void updateQuantity(CartItem item, int newQuantity) {
        if (newQuantity > 0) {
            item.setQuantity(newQuantity);
        } else {
            cartItems.remove(item);
        }
    }

    /**
    * Oblicza całkowitą wartość koszyka.
    * @return suma cen wszystkich produktów
    */
    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getItemTotal();
        }
        return total;
    }

    public Order checkout() {
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Koszyk jest pusty");
        }

        // Tworzenie zamówienia z pozycji koszyka
        Order order = new Order(customer, this);
        
        // Czyszczenie koszyka
        cartItems.clear();
        
        return order;
    }

    // Gettery
    public int getCartId() { return cartId; }
    public Customer getCustomer() { return customer; }
    public List<CartItem> getCartItems() { return new ArrayList<>(cartItems); }

    @Override
    public String toString() {
        return "Koszyk #" + cartId + " (Wartość: " + calculateTotal() + " zł)";
    }
}

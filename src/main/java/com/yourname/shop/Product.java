package com.yourname.shop;

/**
 * Klasa reprezentująca produkt w sklepie
 */
public class Product {
    private String id;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;
    
    // Konstruktor
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = 0;
        this.description = "";
    }
    
    // Gettery i settery
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getStockQuantity() { return stockQuantity; }
    
    public void setDescription(String description) { 
        this.description = description; 
    }
    
    public void setStockQuantity(int quantity) { 
        this.stockQuantity = quantity; 
    }
    
    @Override
    public String toString() {
        return name + " - " + price + " zł (ID: " + id + ")";
    }
}

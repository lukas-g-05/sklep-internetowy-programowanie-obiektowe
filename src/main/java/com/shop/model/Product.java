package com.shop.model;

public class Product implements Discountable{
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private Category category;
    @Override
    public double getDiscountPrice() {
        return this.price * 0.90; //  10% zniżki dla każdego produktu
    }

    public Product(int productId, String name, String description, double price, int stockQuantity, Category category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public boolean isAvailable() {
        return stockQuantity > 0;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
        } else {
            throw new IllegalArgumentException("Niewystarczająca ilość produktu w magazynie");
        }
    }

    // Gettery i settery
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    @Override
    public String toString() {
        return name + " - " + price + " zł (Dostępne: " + stockQuantity + ")";
    }
}

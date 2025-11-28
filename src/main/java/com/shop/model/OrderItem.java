package com.shop.model;

public class OrderItem {
    private String productName;
    private double unitPrice;
    private int quantity;

    public OrderItem(Product product, int quantity, double unitPrice) {
        this.productName = product.getName();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double getItemTotal() {
        return unitPrice * quantity;
    }

    // Gettery
    public String getProductName() { return productName; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return productName + " x " + quantity + " @ " + unitPrice + " zł = " + getItemTotal() + " zł";
    }
}

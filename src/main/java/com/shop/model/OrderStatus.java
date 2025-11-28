package com.shop.model;

public enum OrderStatus {
    PENDING("Oczekujące"),
    PAID("Opłacone"),
    PROCESSING("W trakcie realizacji"),
    SHIPPED("Wysłane"),
    DELIVERED("Dostarczone"),
    CANCELLED("Anulowane");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}

package com.shop.model;

public enum PaymentStatus {
    PENDING("Oczekujące"),
    COMPLETED("Zakończone"),
    FAILED("Nieudane"),
    REFUNDED("Zwrócone");

    private final String description;

    PaymentStatus(String description) {
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

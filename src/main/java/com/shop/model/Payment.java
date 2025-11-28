package com.shop.model;

import java.util.Date;

public abstract class Payment {
    private int paymentId;
    private double amount;
    private Date paymentDate;
    private PaymentStatus status;
    private static int paymentCounter = 0;

    public Payment(double amount) {
        this.paymentId = ++paymentCounter;
        this.amount = amount;
        this.paymentDate = new Date();
        this.status = PaymentStatus.PENDING;
    }

    // Metoda abstrakcyjna - musi być zaimplementowana w klasach pochodnych
    public abstract boolean processPayment();

    // Gettery i settery
    public int getPaymentId() { return paymentId; }
    public double getAmount() { return amount; }
    public Date getPaymentDate() { return paymentDate; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
}

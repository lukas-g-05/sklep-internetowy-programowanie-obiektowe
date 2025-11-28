package com.shop.model;

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(double amount, String cardNumber, String expiryDate, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment() {
        // Symulacja procesu płatności
        System.out.println("Przetwarzanie płatności kartą: " + maskCardNumber(cardNumber));
        
        // Symulacja - zawsze się udaje dla demonstracji
        setStatus(PaymentStatus.COMPLETED);
        System.out.println("Płatność zakończona pomyślnie!");
        return true;
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() > 4) {
            return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
        }
        return cardNumber;
    }

    // Gettery
    public String getCardNumber() { return cardNumber; }
    public String getExpiryDate() { return expiryDate; }
    public String getCvv() { return cvv; }
}

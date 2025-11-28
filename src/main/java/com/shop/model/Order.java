package com.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private Date orderDate;
    private Customer customer;
    private List<OrderItem> orderItems;
    private double totalAmount;
    private OrderStatus status;
    private Payment payment;
    private static int orderCounter = 0;

    public Order(Customer customer, ShoppingCart cart) {
        this.orderId = ++orderCounter;
        this.orderDate = new Date();
        this.customer = customer;
        this.status = OrderStatus.PENDING;
        this.orderItems = new ArrayList<>();
        
        // Konwersja CartItem na OrderItem
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem(
                cartItem.getProduct(), 
                cartItem.getQuantity(), 
                cartItem.getProduct().getPrice()
            );
            orderItems.add(orderItem);
            
            // Zmniejsz stan magazynowy
            cartItem.getProduct().decreaseStock(cartItem.getQuantity());
        }
        
        calculateTotal();
        customer.addOrderToHistory(this);
    }

    public void calculateTotal() {
        totalAmount = 0;
        for (OrderItem item : orderItems) {
            totalAmount += item.getItemTotal();
        }
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        if (payment.processPayment()) {
            this.status = OrderStatus.PAID;
        }
    }

    // Gettery
    public int getOrderId() { return orderId; }
    public Date getOrderDate() { return orderDate; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getOrderItems() { return new ArrayList<>(orderItems); }
    public double getTotalAmount() { return totalAmount; }
    public OrderStatus getStatus() { return status; }
    public Payment getPayment() { return payment; }

    @Override
    public String toString() {
        return "Zamówienie #" + orderId + " - " + totalAmount + " zł - " + status;
    }
}

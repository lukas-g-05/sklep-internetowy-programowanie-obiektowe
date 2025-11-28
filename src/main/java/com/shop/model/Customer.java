package com.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String address;
    private ShoppingCart shoppingCart;
    private List<Order> orderHistory;

    public Customer(int userId, String email, String password, String firstName, String lastName, String address) {
        super(userId, email, password, firstName, lastName);
        this.address = address;
        this.shoppingCart = new ShoppingCart(this);
        this.orderHistory = new ArrayList<>();
    }

    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public ShoppingCart getShoppingCart() { return shoppingCart; }
}

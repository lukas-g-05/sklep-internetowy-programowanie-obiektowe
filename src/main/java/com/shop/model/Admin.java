package com.shop.model;

public class Admin extends User {
    public Admin(int userId, String email, String password, String firstName, String lastName) {
        super(userId, email, password, firstName, lastName);
    }

    public void addProduct(Product product, ProductRepository repository) {
        repository.addProduct(product);
        System.out.println("Produkt dodany: " + product.getName());
    }

    public void updateProduct(Product product, String name, String description, double price, int stockQuantity) {
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        System.out.println("Produkt zaktualizowany: " + product.getName());
    }

    public void deleteProduct(Product product, ProductRepository repository) {
        repository.removeProduct(product);
        System.out.println("Produkt usunięty: " + product.getName());
    }

    public void updateOrderStatus(Order order, OrderStatus newStatus) {
        order.updateStatus(newStatus);
        System.out.println("Status zamówienia " + order.getOrderId() + " zmieniony na: " + newStatus);
    }
}

package com.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;
    private static ProductRepository instance;

    // Prywatny konstruktor - wzorzec Singleton
    private ProductRepository() {
        this.products = new ArrayList<>();
    }

    // Metoda statyczna do uzyskania instancji
    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public List<Product> findProductsByCategory(Category category) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                result.add(product);
            }
        }
        return result;
    }

    // Metoda statyczna pomocnicza
    public static boolean isProductAvailable(Product product) {
        return product != null && product.isAvailable();
    }
}

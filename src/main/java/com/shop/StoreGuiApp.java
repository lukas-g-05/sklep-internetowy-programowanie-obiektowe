package com.shop;

import com.shop.model.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class StoreGuiApp {
    private static ProductRepository repository = ProductRepository.getInstance();
    
    // ZMIANA 1: Użytkownicy są stworzeni RAZ jako pola statyczne.
    // Dzięki temu dane nie znikają po wylogowaniu.
    private static Customer demoCustomer = new Customer(1, "student@wp.pl", "pass", "student", "Kowalski", "Warszawa");
    private static Admin demoAdmin = new Admin(99, "admin@wp.pl", "admin", "Szef", "Admin");
    
    // ZMIANA 2: Globalna lista wszystkich zamówień w sklepie (żeby Admin je widział)
    private static List<Order> allOrdersInShop = new ArrayList<>();

    public static void main(String[] args) {
        initializeData(); 

        while (true) {
            String[] roles = {"Klient (student)", "Administrator", "Wyjdź"};
            int choice = JOptionPane.showOptionDialog(null, "Wybierz tryb logowania:", "Sklep - Logowanie",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);

            if (choice == 0) { 
                // Używamy ISTNIEJĄCEGO obiektu, a nie "new Customer"
                runCustomerPanel(demoCustomer);
            } else if (choice == 1) { 
                // Używamy ISTNIEJĄCEGO admina
                runAdminPanel(demoAdmin);
            } else { 
                System.exit(0);
            }
        }
    }

    // --- PANEL KLIENTA ---
    private static void runCustomerPanel(Customer customer) {
        boolean loggedIn = true;
        while (loggedIn) {
            String[] options = {"Katalog Produktów", "Dodaj do Koszyka", "Pokaż Koszyk", "Złóż Zamówienie", "Historia Zamówień", "Wyloguj"};
            int choice = JOptionPane.showOptionDialog(null, "Witaj " + customer.getFirstName() + "!", "Panel Klienta",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: showProductCatalog(); break;
                case 1: addToCart(customer); break;
                case 2: showCart(customer); break;
                case 3: checkout(customer); break;
                case 4: showHistory(customer); break;
                case 5: default: loggedIn = false;
            }
        }
    }

    // --- PANEL ADMINA ---
    private static void runAdminPanel(Admin admin) {
        boolean loggedIn = true;
        while (loggedIn) {
            String[] options = {"Lista Produktów", "Dodaj Nowy Produkt", "Zarządzaj Zamówieniami", "Wyloguj"};
            int choice = JOptionPane.showOptionDialog(null, "Panel Administratora", "Admin Mode",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: showProductCatalog(); break;
                case 1: addNewProduct(admin); break;
                case 2: manageOrders(admin); break; // Nowa metoda do zarządzania
                case 3: default: loggedIn = false;
            }
        }
    }

    // --- LOGIKA ---

    private static void checkout(Customer customer) {
        try {
            // Logika zamówienia
            Order order = customer.getShoppingCart().checkout();
            
            // Symulacja płatności
            String cardNumber = JOptionPane.showInputDialog("Podaj numer karty (symulacja):", "1234-5678-9012-3456");
            if (cardNumber != null) {
                CreditCardPayment payment = new CreditCardPayment(order.getTotalAmount(), cardNumber, "12/30", "123");
                order.setPayment(payment);
                
                // ZMIANA 3: Dodajemy zamówienie do globalnej listy, żeby Admin je widział!
                allOrdersInShop.add(order);
                
                JOptionPane.showMessageDialog(null, "Zamówienie złożone!\nID: " + order.getOrderId() + "\nStatus: " + order.getStatus());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd: " + e.getMessage());
        }
    }

    private static void manageOrders(Admin admin) {
        if (allOrdersInShop.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Brak zamówień w systemie.");
            return;
        }

        // Budujemy listę zamówień do wyświetlenia w okienku
        String[] orderList = new String[allOrdersInShop.size()];
        for (int i = 0; i < allOrdersInShop.size(); i++) {
            Order o = allOrdersInShop.get(i);
            orderList[i] = "ID: " + o.getOrderId() + " | " + o.getCustomer().getEmail() + " | Status: " + o.getStatus();
        }

        // Admin wybiera zamówienie z listy
        String selectedStr = (String) JOptionPane.showInputDialog(null, "Wybierz zamówienie do edycji:",
                "Zarządzanie Zamówieniami", JOptionPane.QUESTION_MESSAGE, null, orderList, orderList[0]);

        if (selectedStr != null) {
            // Wyciągamy ID z napisu (prosty parsing)
            int idIndex = selectedStr.indexOf("|");
            String idPart = selectedStr.substring(4, idIndex).trim();
            int orderId = Integer.parseInt(idPart);

            // Szukamy obiektu zamówienia
            Order selectedOrder = null;
            for(Order o : allOrdersInShop) {
                if(o.getOrderId() == orderId) {
                    selectedOrder = o;
                    break;
                }
            }

            // Zmieniamy status
            if (selectedOrder != null) {
                OrderStatus[] statuses = OrderStatus.values();
                OrderStatus newStatus = (OrderStatus) JOptionPane.showInputDialog(null, "Obecny status: " + selectedOrder.getStatus() + "\nWybierz nowy:",
                        "Zmiana Statusu", JOptionPane.QUESTION_MESSAGE, null, statuses, selectedOrder.getStatus());
                
                if (newStatus != null) {
                    admin.updateOrderStatus(selectedOrder, newStatus);
                    JOptionPane.showMessageDialog(null, "Status zaktualizowany!");
                }
            }
        }
    }

    // --- POZOSTAŁE METODY BEZ WIĘKSZYCH ZMIAN ---
    
    private static void showProductCatalog() {
        StringBuilder sb = new StringBuilder("=== KATALOG ===\n");
        for (Product p : repository.getAllProducts()) {
            sb.append("[").append(p.getProductId()).append("] ").append(p.getName())
              .append(" - ").append(p.getPrice()).append(" zł (Ilość: ").append(p.getStockQuantity()).append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void addToCart(Customer customer) {
        String idStr = JOptionPane.showInputDialog("Podaj ID produktu:");
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr);
            Product p = repository.findProductById(id);
            if (p != null) {
                String qtyStr = JOptionPane.showInputDialog("Ilość:");
                int qty = Integer.parseInt(qtyStr);
                customer.getShoppingCart().addItem(p, qty);
                JOptionPane.showMessageDialog(null, "Dodano.");
            } else {
                JOptionPane.showMessageDialog(null, "Brak produktu o takim ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd danych.");
        }
    }

    private static void showCart(Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        StringBuilder sb = new StringBuilder("=== KOSZYK ===\n");
        if(cart.getCartItems().isEmpty()) sb.append("(pusty)\n");
        for (CartItem item : cart.getCartItems()) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("\nSUMA: ").append(cart.calculateTotal()).append(" zł");
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void showHistory(Customer customer) {
        StringBuilder sb = new StringBuilder("=== HISTORIA ZAMÓWIEŃ ===\n");
        if (customer.getOrderHistory().isEmpty()) sb.append("Brak zamówień.\n");
        
        for (Order o : customer.getOrderHistory()) {
            sb.append("Zamówienie #").append(o.getOrderId())
              .append(" | ").append(o.getTotalAmount()).append(" zł")
              .append(" | STATUS: ").append(o.getStatus()) // Tutaj będzie widać zmianę od Admina!
              .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void addNewProduct(Admin admin) {
        try {
            String name = JOptionPane.showInputDialog("Nazwa:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Cena:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Ilość:"));
            Category cat = new Category(1, "Ogólne", "Różne");
            Product newProduct = new Product(repository.getAllProducts().size() + 1, name, "Opis", price, stock, cat);
            admin.addProduct(newProduct, repository);
            JOptionPane.showMessageDialog(null, "Dodano.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd.");
        }
    }

    private static void initializeData() {
        Category elec = new Category(1, "Elektronika", "Sprzęt");
        repository.addProduct(new Product(1, "Laptop Dell", "Opis", 3500.0, 5, elec));
        repository.addProduct(new Product(2, "Mysz Logitech", "Opis", 120.0, 20, elec));
        repository.addProduct(new Product(3, "Klawiatura", "Opis", 250.0, 10, elec));
    }
}
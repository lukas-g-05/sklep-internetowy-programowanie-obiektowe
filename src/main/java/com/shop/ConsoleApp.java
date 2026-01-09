package com.shop;

import com.shop.model.*;
import java.util.Scanner;

public class ConsoleApp {
    private static ProductRepository repository = ProductRepository.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        initializeData(); // Dodajemy przykładowe dane na start
        
        System.out.println("=== WITAJ W SKLEPIE INTERNETOWYM ===");
        
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                if (currentUser instanceof Admin) {
                    showAdminMenu((Admin) currentUser);
                } else if (currentUser instanceof Customer) {
                    showCustomerMenu((Customer) currentUser);
                }
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n1. Zaloguj jako Klient (jan@test.pl / pass)");
        System.out.println("2. Zaloguj jako Admin (admin@test.pl / admin)");
        System.out.println("0. Wyjdź");
        System.out.print("Wybór: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                // Symulacja logowania klienta (w prawdziwej apce szukalibyśmy w bazie użytkowników)
                currentUser = new Customer(1, "jan@test.pl", "pass", "Jan", "Kowalski", "Warszawa");
                System.out.println("Zalogowano jako Klient!");
                break;
            case "2":
                currentUser = new Admin(99, "admin@test.pl", "admin", "Szef", "Admin");
                System.out.println("Zalogowano jako Admin!");
                break;
            case "0":
                System.out.println("Do widzenia!");
                System.exit(0);
                break;
            default:
                System.out.println("Nieznana opcja.");
        }
    }

    private static void showCustomerMenu(Customer customer) {
        System.out.println("\n--- PANEL KLIENTA ---");
        System.out.println("1. Przeglądaj produkty");
        System.out.println("2. Dodaj produkt do koszyka");
        System.out.println("3. Pokaż koszyk");
        System.out.println("4. Złóż zamówienie (Checkout)");
        System.out.println("5. Moje zamówienia");
        System.out.println("0. Wyloguj");
        System.out.print("Wybór: ");

        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1":
                    repository.getAllProducts().forEach(System.out::println);
                    break;
                case "2":
                    System.out.print("Podaj ID produktu: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Product p = repository.findProductById(id);
                    if (p != null) {
                        System.out.print("Podaj ilość: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        customer.getShoppingCart().addItem(p, qty);
                        System.out.println("Dodano do koszyka.");
                    } else {
                        System.out.println("Nie znaleziono produktu.");
                    }
                    break;
                case "3":
                    System.out.println(customer.getShoppingCart());
                    for(CartItem item : customer.getShoppingCart().getCartItems()) {
                        System.out.println(" - " + item);
                    }
                    break;
                case "4":
                    Order order = customer.getShoppingCart().checkout();
                    System.out.println("Zamówienie złożone! ID: " + order.getOrderId());
                    // Tutaj można dodać symulację płatności
                    break;
                case "5":
                    for(Order o : customer.getOrderHistory()) {
                        System.out.println(o);
                    }
                    break;
                case "0":
                    currentUser = null;
                    break;
                default:
                    System.out.println("Nieznana opcja.");
            }
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    private static void showAdminMenu(Admin admin) {
        System.out.println("\n--- PANEL ADMINA ---");
        System.out.println("1. Dodaj nowy produkt");
        System.out.println("2. Wyświetl wszystkie produkty");
        System.out.println("0. Wyloguj");
        System.out.print("Wybór: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Nazwa: ");
                String name = scanner.nextLine();
                System.out.print("Cena: ");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.print("Ilość: ");
                int stock = Integer.parseInt(scanner.nextLine());
                
                // Tworzymy domyślną kategorię dla uproszczenia
                Category cat = new Category(1, "Ogólne", "Różne");
                Product newProduct = new Product(repository.getAllProducts().size() + 1, name, "Opis", price, stock, cat);
                
                admin.addProduct(newProduct, repository);
                break;
            case "2":
                repository.getAllProducts().forEach(System.out::println);
                break;
            case "0":
                currentUser = null;
                break;
            default:
                System.out.println("Nieznana opcja.");
        }
    }

    private static void initializeData() {
        Category elec = new Category(1, "Elektronika", "Sprzęt");
        repository.addProduct(new Product(1, "Laptop", "Dell", 3000.0, 5, elec));
        repository.addProduct(new Product(2, "Myszka", "Logitech", 100.0, 20, elec));
        repository.addProduct(new Product(3, "Klawiatura", "Razer", 250.0, 10, elec));
    }
}
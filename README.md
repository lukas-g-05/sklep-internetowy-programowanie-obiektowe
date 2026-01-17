# System Sklepu Internetowego

Projekt zaliczeniowy z Programowania Obiektowego. Aplikacja symulująca działanie sklepu internetowego z interfejsem graficznym (GUI), obsługą koszyka, zamówień oraz panelem administratora.

## Jak uruchomić projekt
1. Wymagania: Java JDK 17 lub nowsza.
2. Sklonuj repozytorium lub pobierz pliki.
3. Otwórz projekt w IDE (IntelliJ IDEA, Eclipse, VS Code).
4. Znajdź plik główny: `src/main/java/com/shop/StoreGuiApp.java`.
5. Uruchom metodę `main` w tym pliku.

> **Uwaga:** Aplikacja uruchamia się w trybie okienkowym. Po starcie wybierz rolę (Klient lub Administrator).

## Technologie i Narzędzia
* **Język:** Java
* **GUI:** Java Swing (JOptionPane)
* **Testy:** JUnit 5
* **Repozytorium:** Git & GitHub

## Zastosowane koncepcje obiektowe
Projekt realizuje kluczowe założenia paradygmatu OOP:
* **Dziedziczenie:** Klasa bazowa `User`, po której dziedziczą `Customer` i `Admin`. Klasa bazowa `Payment` dla `CreditCardPayment`.
* **Polimorfizm:** Metoda `processPayment()` w klasach płatności.
* **Hermetyzacja:** Pola prywatne z dostępem przez gettery i settery.
* **Abstrakcja:** Klasy abstrakcyjne `User` i `Payment`.
* **Interfejsy:** Interfejs `Discountable` implementowany przez produkty.
* **Wzorce Projektowe:**
    * **Singleton:** Klasa `ProductRepository` (centralna baza produktów).
* **Enum (Typy wyliczeniowe):** `Category`, `OrderStatus`, `PaymentStatus`.

---

## Funkcjonalności

### 1. Zarządzanie Produktami
Ta grupa funkcjonalności dotyczy przeglądania i modyfikacji asortymentu sklepu.

**Dla Klienta:**
- **Przeglądanie katalogu:** Możliwość wyświetlenia listy wszystkich dostępnych produktów.
- **Wyszukiwanie i filtrowanie:** Funkcjonalność umożliwiająca wyszukiwanie produktów po nazwie oraz zawężanie wyników przy użyciu filtrów (np. kategoria).

**Dla Administratora:**
- **Dodawanie produktów:** Możliwość wprowadzenia nowego produktu do katalogu (z określeniem nazwy, opisu, ceny, ilości itp.).
- **Edycja produktów:** Możliwość modyfikacji istniejących produktów (zmiana ceny, opisu, stanu magazynowego).
- **Usuwanie produktów:** Możliwość trwałego usunięcia produktu z katalogu sklepu.

### 2. Koszyk Zakupowy
Funkcjonalności związane z tymczasowym gromadzeniem produktów przed finalizacją zakupu.

**Dla Klienta:**
- **Dodawanie do koszyka:** Możliwość dodania wybranego produktu w określonej ilości do wirtualnego koszyka.
- **Podgląd koszyka:** Wyświetlenie aktualnej zawartości koszyka, w tym listy produktów, ich ilości oraz łącznej ceny.
- **Modyfikacja koszyka:** Możliwość zmiany ilości danego produktu w koszyku lub całkowitego usunięcia go z koszyka.

### 3. Składanie i Obsługa Zamówień
Proces finalizacji zakupu oraz śledzenia statusu zamówienia.

**Dla Klienta:**
- **Składanie zamówienia:** Proces finalizacji zakupu, podczas którego zawartość koszyka jest konwertowana na stałe, nieedytowalne zamówienie.
- **Historia zamówień:** Podgląd listy wszystkich wcześniej złożonych zamówień.
- **Status zamówienia:** Możliwość śledzenia aktualnego statusu swojego zamówienia (np. "Złożone", "Opłacone", "Wysłane").

**Dla Administratora:**
- **Zmiana statusu zamówienia:** Możliwość aktualizowania statusu zamówień klientów (np. zmiana ze "Złożone" na "W trakcie realizacji" lub "Wysłane").

### 4. System Płatności
Funkcjonalność odpowiedzialna za symulację opłacenia zamówienia.

**Dla Klienta:**
- **Wybór metody płatności:** Możliwość wyboru sposobu zapłaty (np. Przelew, Karta, Blik) podczas finalizacji zamówienia.
- **Symulacja płatności:** Przeprowadzenie symulacji procesu płatności, której pomyślne zakończenie skutkuje automatyczną zmianą statusu zamówienia na "Opłacone".

---

## Autorzy
Łuaksz Grobelny
Michał Łażewski

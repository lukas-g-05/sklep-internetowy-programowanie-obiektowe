package com.shop.model;

public enum Category {
    ELEKTRONIKA("Sprzęt elektroniczny, laptopy, myszki"),
    KSIAZKI("Literatura, podręczniki, ebooki"),
    ODZIEZ("Ubrania damskie i męskie"),
    DOM_I_OGROD("Akcesoria do domu i ogrodu");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    // Nadpisujemy toString, żeby w GUI ładnie wyświetlało nazwę (np. "Elektronika" zamiast "ELEKTRONIKA")
    @Override
    public String toString() {
        // Zamienia np. "DOM_I_OGROD" na "Dom i ogrod"
        return name().charAt(0) + name().substring(1).toLowerCase().replace('_', ' ');
    }
}
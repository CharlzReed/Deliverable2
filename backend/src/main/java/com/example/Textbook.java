package com.example;

public class Textbook extends Item {
    private String subject;
    private String isbn;

    public Textbook(int itemID, String name, LocationType locationInLibrary, double cost, StatusType statusType,
            String subject, String isbn) {
        super(itemID, name, ItemType.TEXTBOOK, locationInLibrary, cost, statusType);
        this.subject = subject;
        this.isbn = isbn;
    }

    public String getSubject() {
        return subject;
    }

    public String getISBN() {
        return isbn;
    }

    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%f,%s,%s,%s", itemID, name, itemType, locationInLibrary, cost, statusType,
                subject, isbn);
    }
}

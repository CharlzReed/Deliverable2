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
}

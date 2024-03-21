package com.example;

public class Book extends Item {
    private String author;
    private int publicationYear;

    public Book(int itemID, String name, LocationType locationInLibrary, double cost, StatusType statusType,
            String author, int publicationYear) {
        super(itemID, name, ItemType.BOOK, locationInLibrary, cost, statusType);
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%f,%s,%s,%d", itemID, name, itemType, locationInLibrary, cost, statusType,
                author, publicationYear);

    }
}

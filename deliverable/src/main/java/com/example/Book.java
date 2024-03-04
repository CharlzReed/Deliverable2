package com.example;

public class Book extends Item {
    private String author;
    private String ISBN;

    public Book(int itemID, String title, int copiesAvailable, boolean canBePurchased, String author, String ISBN) {
        super(itemID, title, copiesAvailable, canBePurchased);
        this.author = author;
        this.ISBN = ISBN;
    }
}

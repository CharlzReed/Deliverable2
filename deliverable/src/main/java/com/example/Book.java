package com.example;

public class Book extends Item {
    private String author;
    private String ISBN;

    public Book(String title, int copiesAvailable, boolean canBePurchased, String author, String ISBN,
            ItemType itemType, double price) {
        super(title, copiesAvailable, canBePurchased, ItemType.BOOK, price);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public String toStringCSV() {
        // To Kill a Mockingbird,7,Cat,True,BOOK,7.99,9780061120084,,0,

        String message = this.title + "," + this.copiesAvailable + "," + this.author + "," + this.canBePurchased
                + ",BOOK," + this.price + "," +
                this.ISBN + ",,0,";

        return message;
    }

}

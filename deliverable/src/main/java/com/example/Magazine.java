package com.example;

public class Magazine extends Item {
    private String issue;
    private String author;

    public Magazine(String title, int copiesAvailable, String author, boolean canBePurchased, String issue,
            ItemType itemType,
            double price) {
        super(title, copiesAvailable, canBePurchased, ItemType.MAGAZINE, price);
        this.author = author;
        this.issue = issue;
    }

    public String getIssue() {
        return this.issue;
    }

    @Override
    public String toStringCSV() {
        // National Geographic,12,David,True,MAGAZINE,5.99,,,0,10
        String message = this.title + "," + this.copiesAvailable + "," + this.author + "," + this.canBePurchased
                + ",MAGAZINE," + this.price + ",,,0," + this.issue;

        return message;

    }

}

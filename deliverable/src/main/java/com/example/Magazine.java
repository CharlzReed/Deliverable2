package com.example;

public class Magazine extends Item {
    private String issue;

    public Magazine(int itemID, String title, int copiesAvailable, boolean canBePurchased, String issue) {
        super(itemID, title, copiesAvailable, canBePurchased);
        this.issue = issue;
    }

    public String getIssue() {
        return this.issue;
    }
}

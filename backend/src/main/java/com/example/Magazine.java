package com.example;

public class Magazine extends Item {
    private String issueNumber;
    private String genre;

    public Magazine(int itemID, String name, LocationType locationInLibrary, double cost, StatusType statusType,
            String issueNumber, String genre) {
        super(itemID, name, ItemType.MAGAZINE, locationInLibrary, cost, statusType);
        this.issueNumber = issueNumber;
        this.genre = genre;
    }

    public String getIssueNum() {
        return issueNumber;
    }

    public String getGenre() {
        return genre;
    }

    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%f,%s,%s,%s", itemID, name, itemType, locationInLibrary, cost, statusType,
                issueNumber, genre);
    }
}

package com.example;

public class ItemFactory {
    public static Item createBook(int itemID, String name, LocationType location, double cost, StatusType status,
            String author, int publicationYear) {
        return new Book(itemID, name, location, cost, status, author, publicationYear);
    }

    public static Item createTextbook(int itemID, String name, LocationType location, double cost, StatusType status,
            String subject, String isbn) {
        return new Textbook(itemID, name, location, cost, status, subject, isbn);
    }

    public static Item createMagazine(int itemID, String name, LocationType location, double cost, StatusType status,
            String issueNumber, String genre) {
        return new Magazine(itemID, name, location, cost, status, issueNumber, genre);
    }

    public static Item createCD(int itemID, String name, LocationType location, double cost, StatusType status,
            String artist, int releaseYear) {
        return new CD(itemID, name, location, cost, status, artist, releaseYear);
    }

    public static Item createSubscription(int itemID, String name, LocationType location, double cost,
            StatusType status, String provider, String subscriptionType) {
        return new Subscription(itemID, name, location, cost, status, provider, subscriptionType);
    }

    public static Item createItem(ItemType itemType, int id, String name, LocationType locationType,
            double cost, StatusType pendingApproval) {
        return new Item(id, name, itemType, locationType, cost, pendingApproval);
    }
}

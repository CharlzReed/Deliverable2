package com.example;

public class CD extends Item {
    // Assuming CDs have some unique properties
    private String artist;

    public CD(String title, int copiesAvailable, boolean canBePurchased, String artist,
            ItemType itemType, double price) {
        super(title, copiesAvailable, canBePurchased, ItemType.CD, price);
        this.artist = artist;
    }

    public String getArtist() {
        return this.artist;
    }

    @Override
    public String toStringCSV() {
        // Greatest Hits,10,Bob,True,CD,15.99,,,0,

        String message = this.title + "," + this.copiesAvailable + "," + this.artist + "," + this.canBePurchased
                + ",CD," + this.price + ",,,0,";

        return message;
    }

}

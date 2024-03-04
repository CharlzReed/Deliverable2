package com.example;

public class CD extends Item {
    // Assuming CDs have some unique properties
    private String artist;

    public CD(int itemID, String title, int copiesAvailable, boolean canBePurchased, String artist) {
        super(itemID, title, copiesAvailable, canBePurchased);
        this.artist = artist;
    }

    public String getArtist() {
        return this.artist;
    }

}

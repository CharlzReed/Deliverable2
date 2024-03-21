package com.example;

public class CD extends Item {
    private String artist;
    private int releaseYear;

    public CD(int itemID, String name, LocationType locationInLibrary, double cost, StatusType statusType,
            String artist, int releaseYear) {
        super(itemID, name, ItemType.CD, locationInLibrary, cost, statusType);
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    public String getArtist() {
        return artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%f,%s,%s,%d", itemID, name, itemType, locationInLibrary, cost, statusType,
                artist, releaseYear);
    }
}

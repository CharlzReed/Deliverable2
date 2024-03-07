package com.example;


public class Item {

    public int itemID;
    public String name;
    public ItemType itemType;
    public LocationType locationInLibrary;
    public double cost;
    
    public int copiesAvailable = 20;

    public Item(int id, String name, ItemType itemType, LocationType location, double cost) {
        this.itemID = id;
        this.name = name;
        this.itemType = itemType;
        this.locationInLibrary = location;
        this.cost = cost;
    }


    @Override
    public String toString() {
        return String.format("(itemID=%d,  name=%s,  itemType=%s,  location=%s,  cost=%f)", itemID,name,itemType,locationInLibrary,cost);
    }
    
    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%f", itemID,name,itemType,locationInLibrary,cost);

    }

}
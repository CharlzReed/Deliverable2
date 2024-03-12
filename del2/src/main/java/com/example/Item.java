package com.example;


public class Item {

    public int itemID;
    public String name;
    public ItemType itemType;
    public LocationType locationInLibrary;
    public double cost;
    public StatusType statusType;

    public Item(int id, String name, ItemType itemType, LocationType location, double cost, StatusType statusType) {
        this.itemID = id;
        this.name = name;
        this.itemType = itemType;
        this.locationInLibrary = location;
        this.cost = cost;
        this.statusType = statusType;
    }


    @Override
    public String toString() {
        return String.format("(itemID=%d,  name=%s,  itemType=%s,  location=%s,  cost=%f,   statusType=%s)", itemID,name,itemType,locationInLibrary,cost,statusType);
    }
    
    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%f,%s", itemID,name,itemType,locationInLibrary,cost,statusType);

    }

}
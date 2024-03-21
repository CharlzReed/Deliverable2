package com.example;

public class Item {
    public int itemID;
    public String name;
    public ItemType itemType;
    public LocationType locationInLibrary;
    public double cost;
    public StatusType statusType;
    private String edition;
    private boolean isAvailable;

    public Item(int id, String name, ItemType itemType, LocationType location, double cost, StatusType statusType) {
        this.itemID = id;
        this.name = name;
        this.itemType = itemType;
        this.locationInLibrary = location;
        this.cost = cost;
        this.statusType = statusType;
        this.edition = "";
        this.isAvailable = true;
    }

    public String getName() {
        return this.name;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return String.format(
                "(itemID=%d, name=%s, itemType=%s, location=%s, cost=%f, statusType=%s, edition=%s, available=%s)",
                itemID, name, itemType, locationInLibrary, cost, statusType, edition, isAvailable);
    }

    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%f,%s", itemID, name, itemType, locationInLibrary, cost, statusType);
    }

    // addison
    public ItemType getItemType() {
        return this.itemType;
    }

    // addison
    public String getname() {
        return this.name;
    }

    // addison
    public double getcost() {
        return this.cost;
    }

    // addison
    public StatusType getstatustype() {
        return this.statusType;
    }

    // addison
    public LocationType getlocationInLibrary() {
        return this.locationInLibrary;
    }

    // Added this to see if an item is a subscription in frontend (Probably
    // inefficient but it works) -Moses
    public boolean isSubscription() {
        if (this.itemType.equals(ItemType.SUBSCRIPTION)) {
            return true;
        }

        return false;
    }

}

package com.example;

import java.io.ObjectInputFilter.Status;

public class Item {

    public int itemID;
    public String name;
    public ItemType itemType;
    public LocationType locationInLibrary;
    public double cost;
    public StatusType statusType;
    private ItemContext context;
    private String edition;
    private boolean isAvailable;
    private DiscountStrategy discount = new NoDiscountStrategy();

    public Item(int id, String name, ItemType itemType, LocationType location, double cost, StatusType statusType) {
        this.itemID = id;
        this.name = name;
        this.itemType = itemType;
        this.locationInLibrary = location;
        this.cost = cost;
        this.statusType = statusType;
        this.edition = "";
        this.context = new ItemContext(this);
        if(statusType.equals(StatusType.ACTIVE)){
            isAvailable = true;
        }
        else {
            isAvailable = false;
        }

    }

    public String getEdition() {
        return edition;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public ItemContext getContext() {
        return context;
    }

    @Override
    public String toString() {
        return String.format("(itemID=%d,  name=%s,  itemType=%s,  location=%s,  cost=%f,   statusType=%s)", itemID,
                name, itemType, locationInLibrary, cost, statusType);
    }

    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%f,%s", itemID, name, itemType, locationInLibrary, cost, statusType);

    }

    public void applyDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discount = discountStrategy;
    }

    public double getCostAfterDiscount() {
        return discount.applyDiscount(cost);
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

    public boolean isActive() {
        if (statusType.equals(StatusType.ACTIVE)) {
            return true;
        } else {
            return false;
        }
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

    public String getName() {
        return this.name;
    }

}
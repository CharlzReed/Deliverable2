package com.example;

public abstract class Item {
    protected static int nextID = 1;
    protected int itemID;
    protected double price;
    protected String title;
    protected ItemType itemType;
    protected int copiesAvailable;
    protected boolean canBePurchased;

    public Item(String title, int copiesAvailable, boolean canBePurchased, ItemType itemType, double price) {
        int newID = nextID++;
        this.itemID = newID;
        this.title = title;
        this.copiesAvailable = copiesAvailable;
        this.canBePurchased = canBePurchased;
        this.itemType = itemType;
        this.price = price;
    }

    public abstract String toStringCSV();

    public void checkOut() {
        if (canBePurchased & copiesAvailable > 0) {
            copiesAvailable--;
        } else {
            // this is where we notify that it isnt available
            System.out.println("Not available for purchase");
        }
    }

    public void returnItem() {
        copiesAvailable++;
    }

    public int getItemID() {
        return this.itemID;
    }

    public String getTitle() {
        return title;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public boolean getCanBePurchased() {
        return canBePurchased;
    }

    // Setters need to specify permissions
    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    protected void setCanBePurchased(boolean canBePurchased) {
        this.canBePurchased = canBePurchased;
    }

}

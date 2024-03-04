package com.example;

public abstract class Item {
    protected int itemID;
    protected String title;
    protected int copiesAvailable;
    protected boolean canBePurchased;

    public Item(int itemID, String title, int copiesAvailable, boolean canBePurchased) {
        this.itemID = itemID;
        this.title = title;
        this.copiesAvailable = copiesAvailable;
        this.canBePurchased = canBePurchased;
    }

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
        return itemID;
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
    protected void setItemID(int itemID) {
        this.itemID = itemID;
    }

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


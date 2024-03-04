package com.example;

public interface Item {
    
    public int itemID = -1;
    public String title = "";
    public int copiesAvailable = -1;
    public boolean canBePerchased = false;

    public void checkOut();
    public void returnItem();

}


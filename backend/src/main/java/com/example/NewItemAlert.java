package com.example;

public class NewItemAlert implements Observer {
    @Override
    public void update(Object arg) {
        if (arg instanceof Item) {
            Item newItem = (Item) arg;
            System.out.println("New item added: " + newItem.name);
        }
    }
}

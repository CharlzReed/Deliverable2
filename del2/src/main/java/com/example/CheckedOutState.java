package com.example;

public class CheckedOutState implements ItemState {
    @Override
    public void checkout(ItemContext context) {
        // Item is already checked out, cannot checkout again.
        System.out.println("Item is already checked out.");
    }

    @Override
    public void returnItem(ItemContext context) {
        // Change state to Available
        context.setState(new AvailableState());
        System.out.println("Item returned and now available.");
    }
}

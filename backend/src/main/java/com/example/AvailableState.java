package com.example;

public class AvailableState implements ItemState {
    @Override
    public void checkout(ItemContext context) {
        Library library = Library.getInstance(); // Access the singleton Library instance
        Item item = context.getItem();
        Integer availableCopies = library.getCopiesAvailable().getOrDefault(item, 0);

        // Decrease the number of available copies by one
        library.getCopiesAvailable().put(item, availableCopies - 1);

        context.setState(new CheckedOutState());
        System.out.println("Item has been checked out.");
    }

    @Override
    public void returnItem(ItemContext context) {
        Library library = Library.getInstance(); // Access the singleton Library instance
        Item item = context.getItem();
        Integer availableCopies = library.getCopiesAvailable().getOrDefault(item, 0);

        // Increase the number of available copies by one
        library.getCopiesAvailable().put(item, availableCopies + 1);

        context.setState(new AvailableState());
        System.out.println("Item returned and now available.");
    }
}

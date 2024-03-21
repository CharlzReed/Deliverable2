package com.example;

public class Main {

    public static void main(String[] args) {
        Library library = Library.getInstance();

        // Assuming CSVReader.readALL() properly initializes the library with users,
        // items, etc.
        CSVReader.readALL();

        // Simulate adding a new user
        library.registerNewUser(
                library.generateRandomNumber(),
                "John Doe",
                "john.doe@example.com",
                "securePassword123",
                UserType.STUDENT,
                100.0);

        Item newItem = new Item(1, "The Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 10.99,
                StatusType.ACTIVE);
        library.addItem(newItem);
        newItem.applyDiscountStrategy(new FreeDiscountStrategy());
        System.out.println("Cost after discount: " + newItem.getCostAfterDiscount());

        // Simulate creating and adding new items

        // Retrieve a user and have them rent the new book
        User user = library.searchUsers("John Doe", 1).get(0);
        user.rentItem(newItem);

        CSVReader.writeALL();
    }
}

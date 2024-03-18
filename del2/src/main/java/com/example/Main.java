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

        // Simulate creating and adding new items
        Item newBook = ItemFactory.createBook(
                library.generateRandomNumber(),
                "The Alchemist",
                LocationType.NORTH_SHELF,
                15.99,
                StatusType.ACTIVE,
                "David Doe",
                2006);
        library.addItem(newBook);

        // Retrieve a user and have them rent the new book
        User user = library.searchUsers("John Doe", 1).get(0);
        user.rentItem(newItem);

        // Print details of all users and their rented items
        for (User u : library.getUsers()) {
            System.out.println(u.getName() + " has rented the following items:");
            for (Item item : u.getRentedItems()) {
                System.out.println("- " + item.name);
            }
            System.out.println("Account balance: " + u.getAccountBalance());
            System.out.println("Overdue charges: " + u.calcOverDueCharge4all(newBook));
            System.out.println("---");
        }
    }
}

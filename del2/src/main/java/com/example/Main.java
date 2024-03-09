package com.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    
    public static void main(String[] args) {
        
        // // Item object creation
        // Item item1 = new Item(IdGenerator.getID(), "book1", ItemType.BOOK, LocationType.NORTH_SHELF, 0.0);
        // Item item2 = new Item(IdGenerator.getID(), "book2", ItemType.BOOK, LocationType.SOUTH_SHELF, 0.0);
        // Item item3 = new Item(IdGenerator.getID(), "book3", ItemType.BOOK, LocationType.EAST_SHELF, 0.0);
        // Item item4 = new Item(IdGenerator.getID(), "CD1", ItemType.CD, LocationType.WEST_SHELF, 0.0);
        // Item item5 = new Item(IdGenerator.getID(), "CD2", ItemType.CD, LocationType.EAST_SHELF, 0.0);
        // Item item6 = new Item(IdGenerator.getID(), "CD3", ItemType.CD, LocationType.EAST_SHELF, 0.0);
        // Item item7 = new Item(IdGenerator.getID(), "magazine1", ItemType.MAGAZINE, LocationType.WEST_SHELF, 0.0);
        // Item item8 = new Item(IdGenerator.getID(), "magazine2", ItemType.MAGAZINE, LocationType.SOUTH_SHELF, 0.0);
        // Item item9 = new Item(IdGenerator.getID(), "magazine3", ItemType.MAGAZINE, LocationType.EAST_SHELF, 0.0);
        // Item item10 = new Item(IdGenerator.getID(), "subscription1", ItemType.SUBSCRIPTION, LocationType.WEST_SHELF, 19.99);
        // Item item11 = new Item(IdGenerator.getID(), "subscription2", ItemType.SUBSCRIPTION, LocationType.WEST_SHELF, 9.99);
        // Item item12 = new Item(IdGenerator.getID(), "subscription3", ItemType.SUBSCRIPTION, LocationType.NORTH_SHELF, 0.99);
        
        // Library.addItem(item1);
        // Library.addItem(item2);
        // Library.addItem(item3);
        // Library.addItem(item4);
        // Library.addItem(item5);
        // Library.addItem(item6);
        // Library.addItem(item7);
        // Library.addItem(item8);
        // Library.addItem(item9);
        // Library.addItem(item10);
        // Library.addItem(item11);
        // Library.addItem(item12);
        
        // // User creation
        // User user1 = new User(IdGenerator.getID(), "visitor1@gmail.com", "visitor1Password", UserType.VISITOR, 86.29);
        // User user2 = new User(IdGenerator.getID(), "visitor2@gmail.com", "visitor2Password", UserType.VISITOR, 88.11);
        // User user3 = new User(IdGenerator.getID(), "student1@gmail.com", "student1Password", UserType.STUDENT, 23.01);
        // User user4 = new User(IdGenerator.getID(), "student2@gmail.com", "student2Password", UserType.STUDENT, 17.55);
        // User user5 = new User(IdGenerator.getID(), "faculty1@gmail.com", "faculty1Password", UserType.FACULTY, 22.31);
        // User user6 = new User(IdGenerator.getID(), "faculty2@gmail.com", "faculty2Password", UserType.FACULTY, 57.15);
        // User user7 = new User(IdGenerator.getID(), "non-faculty1@gmail.com", "non-faculty1Password", UserType.NON_FACULTY, 21.01);
        // User user8 = new User(IdGenerator.getID(), "non-faculty2@gmail.com", "non-faculty2Password", UserType.NON_FACULTY, 87.05);
        // User user9 = new User(IdGenerator.getID(), "admin1@gmail.com", "admin1Password", UserType.ADMIN, 87.05);

        // Library.addUser(user1);
        // Library.addUser(user2);
        // Library.addUser(user3);
        // Library.addUser(user4);
        // Library.addUser(user5);
        // Library.addUser(user6);
        // Library.addUser(user7);
        // Library.addUser(user8);
        // Library.addUser(user9);

        // Library.users.get(0).rentItem(Library.items.get(0));
        // Library.users.get(0).rentItem(Library.items.get(3));
        // Library.users.get(0).rentItem(Library.items.get(6));
        // Library.users.get(0).rentItem(Library.items.get(9));
        
        // Library.users.get(1).rentItem(Library.items.get(1));
        // Library.users.get(1).rentItem(Library.items.get(4));
        // Library.users.get(1).rentItem(Library.items.get(7));
        // Library.users.get(1).rentItem(Library.items.get(10));

        // // Course creation
        // Course course1 = new Course(IdGenerator.getID(), "Introduction to Programming", "CS101", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 5, 15), item1.itemID);
        // Course course2 = new Course(IdGenerator.getID(), "Data Structures and Algorithms", "CS201", LocalDate.of(2024, 3, 15), LocalDate.of(2024, 6, 30), item2.itemID);
        // Course course3 = new Course(IdGenerator.getID(), "Artificial Intelligence", "CS401", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 7, 15), item3.itemID);

        // Library.addCourse(course1);
        // Library.addCourse(course2);
        // Library.addCourse(course3);
        
        // user3.addCourse(course1);
        // user3.addCourse(course2);
        // user4.addCourse(course2);
        // user4.addCourse(course3);

        // System.out.println(user3.courses.size());
        // System.out.println(user4.courses.size());

        CSVReader.readALL();


        System.out.println("\nitems");
        System.out.println(Library.items);
        
        System.out.println("\nusers");
        System.out.println(Library.users);

        System.out.println("\ncourses");
        System.out.println(Library.courses);
        
        System.out.println("\ncopies available");
        System.out.println(Library.copiesAvailable);

        // System.out.println("\n");
        // System.out.println(Library.textbook2course);

        // Library.users.get(3).add2Balance(100);


        
        // Library.requestItem("SampleBOOK234", ItemType.BOOK, LocationType.EAST_SHELF, 0);
        // Library.requestItem("SampleBOOK456", ItemType.BOOK, LocationType.EAST_SHELF, 0);
        // Library.requestItem("SampleBOOK678", ItemType.BOOK, LocationType.EAST_SHELF, 0);
        
        // Library.processRequest(2);
        
        // System.out.println("\n");
        
        
        // System.out.println("\n_______");
        // ArrayList<Item> c = Library.searchUsers("student", 1).get(0).rentedItems;
        // System.err.println(c + "\n\n");;
        
        // Library.requestItem("test1", ItemType.CD, LocationType.EAST_SHELF, 0.0);
        // Library.processRequest(1);
        
        // System.out.println("\n_______");
        // System.out.println("\n_______");
        // System.out.println("\n_______");

        
        CSVReader.writeALL();
        
    }
}

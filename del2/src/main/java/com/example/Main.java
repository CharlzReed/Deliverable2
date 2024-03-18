package com.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    
    public static void main(String[] args) {
        Library library = Library.getInstance();
        // // Item object creation

        Item magazine = ItemFactory.createMagazine(3, "National Geographic", LocationType.SOUTH_SHELF, 5.99, StatusType.ACTIVE, "2024-03", "Science");
        library.addItem(magazine);
        
        Item cd = ItemFactory.createCD(4, "Abbey Road", LocationType.WEST_SHELF, 19.99, StatusType.ACTIVE, "The Beatles", 1969);
        library.addItem(cd);
        
        Item subscription = ItemFactory.createSubscription(5, "Premium News Service", LocationType.EAST_SHELF, 99.99, StatusType.ACTIVE, "Global News", "Annual");
        library.addItem(subscription);
        

        CSVReader.readALL();

        Library lib = Library.getInstance();
        System.out.println("\nitems");
        System.out.println(lib.getItems());
        
        System.out.println("\nusers");
        System.out.println(lib.getUsers());

        System.out.println("\ncourses");
        System.out.println(lib.getCourses());
        
        System.out.println("\ncopies available");
        System.out.println(lib.getCopiesAvailable());


        
        CSVReader.writeALL();
        
    }
}

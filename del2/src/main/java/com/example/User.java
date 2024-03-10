package com.example;

import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;


class User {

    public int userID;
    public String name;
    public String email;
    public String password;
    public UserType userType;
    public double accountBalance;

    public ArrayList<Course> courses;
    public ArrayList<Item> rentedItems;
    public HashMap<Item, LocalDate> rentLog;

    public User(int id, String name, String email, String password, UserType userType, double accountBalance) {
        this.userID = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.accountBalance = accountBalance;
        this.courses = new ArrayList<>();
        this.rentedItems = new ArrayList<>();
        this.rentLog = new HashMap<>();
    }


    // for adding items WITHOUT logging date. Used only when reconstructing the objects.
    public boolean addItem(Item item) {

        if (threeOverDue()) return false;

        if (!Library.copiesAvailable.containsKey(item)) {
            System.out.println("Item doesnt exist");
            return false;
        } 
        if (Library.copiesAvailable.get(item) <= 0) {
            System.out.println("Item has too many copies in use");
            return false;
        }
        if (rentedItems.size() >= 10) {
            System.out.println("You have too many items being rented");
            return false;
        }
        if (accountBalance < item.cost) {
            System.out.println("You have too many items being rented");
            return false;
        }
        
        rentedItems.add(item);
        if (item.itemType != ItemType.SUBSCRIPTION) {
            Library.copiesAvailable.put(item, Library.copiesAvailable.get(item)-1);
        }
        return true;
    }
    
    // for adding items WITH logging date. Used when renting new item.
    public boolean rentItem(Item item) {

        if (threeOverDue()) return false;

        if (!Library.copiesAvailable.containsKey(item)) {
            System.out.println("Item doesnt exist");
            return false;
        } 
        if (Library.copiesAvailable.get(item) <= 0) {
            System.out.println("Item has too many copies in use");
            return false;
        }
        if (rentedItems.size() >= 10) {
            System.out.println("You have too many items being rented");
            return false;
        }
        if (accountBalance < item.cost) {
            System.out.println("You have too many items being rented");
            return false;
        }

        rentedItems.add(item);
        rentLog.put(item, LocalDate.now());
        if (item.itemType != ItemType.SUBSCRIPTION) {
            Library.copiesAvailable.put(item, Library.copiesAvailable.get(item)-1);
        }
        this.accountBalance -= item.cost;
        return true;
    }


    public void returnItem(Item item) {
        this.accountBalance -= calcOverDueCharge4all(item);
        rentedItems.remove(item);
        rentLog.remove(item);
        if (item.itemType != ItemType.SUBSCRIPTION) {
            Library.copiesAvailable.put(item, Library.copiesAvailable.get(item)+1);
        }
    }


    public void addCourse(Course course) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(course.startDate) && currentDate.isBefore(course.endDate)) {
            courses.add(course);
            // System.out.println("Course added successfully.");
        } else {
            System.out.println("Cannot add course. Current date is not between the start and end dates of the course.");
            returnItem(Library.course2textbook.get(course)); // Remove the textbook from the rented items since the course is over
        }
    }


    public void add2Balance(int amount) {
        accountBalance += amount;
    }


    public int daysOverdue(Item item) {
        LocalDate dueDate = rentLog.get(item);
        long daysOverdue = LocalDate.now().toEpochDay() - dueDate.toEpochDay() - 30;
        daysOverdue = (daysOverdue < 0) ? 0 : daysOverdue;
        return (int)daysOverdue;
    }
    


    private boolean threeOverDue() {
        int n = 0;
        for (Item item : rentedItems) {
            if (daysOverdue(item) > 15) { // When more than 15 days overdue, consider lost
                rentedItems.remove(item);
                rentLog.remove(item);
            } else if (daysOverdue(item) > 0) { // If overdue, charge 0.5/day
                n++;
            }
        }
        return n > 3;
    }
    
    
    private double calcOverDueCharge4all(Item item) {
        return ((double)daysOverdue(item)) * 0.5;
    }


    @Override
    public String toString() {
        return String.format("(userID=%d,  name=%s,  email=%s,  password=%s,  userType=%s,  accountBalance=%f)", userID,name,email,password,userType,accountBalance);
    }
    
    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%s,%f", userID,name,email,password,userType,accountBalance);
    }

}

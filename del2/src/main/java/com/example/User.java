package com.example;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;


class User {

    public int userID;
    public String name;
    public String email;
    public String password;
    public UserType userType;
    public double accountBalance;

    public ArrayList<Course> courses;
    public ArrayList<Item> rentedItems;
    public HashMap<Item, Date> rentLog;

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


    public boolean rentItem(Item item) {
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
        rentLog.put(item, new Date());
        if (item.itemType != ItemType.SUBSCRIPTION) {
            Library.copiesAvailable.put(item, Library.copiesAvailable.get(item)-1);
        }
        this.accountBalance -= item.cost;
        return true;
    }


    public void returnItem(Item item) {
        rentedItems.remove(item);
        rentLog.remove(item);
        if (item.itemType != ItemType.SUBSCRIPTION) {
            Library.copiesAvailable.put(item, Library.copiesAvailable.get(item)+1);
        }
    }


    public void addCourse(Course course) {
        courses.add(course);
    }


    public void add2Balance(int amount) {
        accountBalance += amount;
    }


    @Override
    public String toString() {
        return String.format("(userID=%d,  name=%s,  email=%s,  password=%s,  userType=%s,  accountBalance=%f)", userID,name,email,password,userType,accountBalance);
    }
    
    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%s,%f", userID,name,email,password,userType,accountBalance);
    }

}
package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Library {

    public static HashMap<Item, Integer> copiesAvailable = new HashMap<>();
    public static HashMap<Course, Item> course2textbook = new HashMap<>();
    public static HashMap<Item, Course> textbook2course = new HashMap<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();
    public static HashSet<Integer> noDupes = new HashSet<>();

    public static int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9000) + 1000;
    }

    public static void addItem(Item item) {
        if (noDupes.contains(item.itemID))
            return;
        copiesAvailable.put(item, 20);
        items.add(item);
        noDupes.add(item.itemID);
    }

    public static void addUser(User user) {
        if (noDupes.contains(user.userID))
            return;
        users.add(user);
        noDupes.add(user.userID);
    }

    public static void addCourse(Course course) {
        if (noDupes.contains(course.courseID))
            return;
        courses.add(course);
        noDupes.add(course.courseID);
    }

    public static void assign_course_textbook(Course course, Item textbook) {
        course2textbook.put(course, textbook);
        textbook2course.put(textbook, course);
    }

    public static void requestItem(String name, ItemType itemType, LocationType locationType, double cost) {
        Item tempItem = new Item(generateRandomNumber(), name, itemType, locationType, cost,
                StatusType.PENDING_APPROVAL);
        addItem(tempItem);
    }

    public static void processRequest(int numberOfRequests) {
        int i;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).statusType == StatusType.PENDING_APPROVAL && items.get(i).itemType == ItemType.TEXTBOOK
                    && numberOfRequests > 0) {
                items.get(i).statusType = StatusType.ACTIVE;
                numberOfRequests--;
            }
        }
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).statusType == StatusType.PENDING_APPROVAL && numberOfRequests > 0) {
                items.get(i).statusType = StatusType.ACTIVE;
                numberOfRequests--;
            }
        }
    }

    public static void applyDiscount(Item item) {
        item.cost = 0;
    }

    public static ArrayList<Item> searchItems(String pattern, int numOfResults) {
        ArrayList<Item> retval = new ArrayList<>();
        for (Item item : items) {
            if (item.csvFormat().toLowerCase().contains(pattern.toLowerCase()) && numOfResults > 0) {
                System.out.println(item);
                retval.add(item);
                numOfResults--;
            }
        }
        return retval;
    }

    public static ArrayList<User> searchUsers(String pattern, int numOfResults) {
        ArrayList<User> retval = new ArrayList<>();
        for (User user : users) {
            if (user.csvFormat().toLowerCase().contains(pattern.toLowerCase()) && numOfResults > 0) {
                System.out.println(user);
                retval.add(user);
                numOfResults--;
            }
        }
        return retval;
    }

    public static ArrayList<Course> searchCourses(String pattern, int numOfResults) {
        ArrayList<Course> retval = new ArrayList<>();
        for (Course course : courses) {
            if (course.csvFormat().toLowerCase().contains(pattern.toLowerCase()) && numOfResults > 0) {
                System.out.println(course);
                retval.add(course);
                numOfResults--;
            }
        }
        return retval;
    }

    public static int generateNextUserId() {
        int userId = generateRandomNumber();
        while (noDupes.contains(userId)) {
            userId = generateRandomNumber();
        }
        return userId;
    }

}
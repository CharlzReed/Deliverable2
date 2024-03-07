package com.example;

import java.util.HashMap;
import java.util.ArrayList;


public class Library {

    public static HashMap<Item, Integer> copiesAvailable = new HashMap<>();
    public static HashMap<Course, Item> course2textbook = new HashMap<>();
    public static HashMap<Item,Course > textbook2course = new HashMap<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();




    public static void addItem(Item item) {
        copiesAvailable.put(item, 20);
        items.add(item);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static void assign_course_textbook(Course course, Item textbook) {
        course2textbook.put(course, textbook);
        textbook2course.put(textbook, course);
    }

    public static User findByPassword(String password) {
        User tempUser = null;
        for (int i = 0; i < users.size(); i++) {
            tempUser = users.get(i);
            if (tempUser.password == password) {
                break;
            }
        }
        return tempUser;
    }



}
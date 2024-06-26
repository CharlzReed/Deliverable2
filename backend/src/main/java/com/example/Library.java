package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

//Singleton
public class Library implements Subject {
    private static Library instance;

    // Changed visibility to private to encapsulate the state
    private HashMap<Item, Integer> copiesAvailable = new HashMap<>();
    private HashMap<Course, Item> course2textbook = new HashMap<>();
    private HashMap<Item, Course> textbook2course = new HashMap<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private HashSet<Integer> noDupes = new HashSet<>();
    private List<Observer> observers = new ArrayList<>();
    private List<OverdueListener> overdueListeners = new ArrayList<>();

    private Library() {
        observers.add(new NewItemAlert());
    }
    //MADE BY ADDISON FOR TESTS
    public static Library createlibrary(){
        instance=new Library();
        return instance;

    }
    //MADE BY ADDISON FOR TEST
    public List<Observer> getObservers(){
        return observers;
    }
    //MADE BY ADDISON FOR TEST
    public List<OverdueListener> getOverduelistener(){
        return overdueListeners;
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    //Added for testing purposes -Moses
    public static Library newInstance() {
        instance = new Library();
        return instance;
    }

    public static int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9000) + 1000;
    }

    public void addItem(Item item) {
        if (noDupes.contains(item.itemID))
            return;
        copiesAvailable.put(item, 20);
        items.add(item);
        noDupes.add(item.itemID);
        notifyObservers(item);
    }

    private void addUser(User user) {
        if (!noDupes.contains(user.getUserID())) {
            users.add(user);
            noDupes.add(user.getUserID());
        } else {
            System.out.println("User ID " + user.getUserID() + " already exists.");
        }
    }

    public void registerNewUser(int userID, String name, String email, String password, UserType userType,
            double accountBalance) {
        if (noDupes.contains(userID)) {
            System.out.println("User ID already exists.");
            return;
        }

        User newUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        addUser(newUser);
    }

    public void addCourse(Course course) {
        if (noDupes.contains(course.courseID))
            return;
        courses.add(course);
        noDupes.add(course.courseID);
    }

    public void assign_course_textbook(Course course, Item textbook) {
        course2textbook.put(course, textbook);
        textbook2course.put(textbook, course);
    }

    public void requestItem(String name, ItemType itemType, LocationType locationType, double cost) {
        Item tempItem = ItemFactory.createItem(itemType, generateRandomNumber(), name, locationType, cost,
                StatusType.PENDING_APPROVAL);
        this.addItem(tempItem);
    }

    public void processRequest(int numberOfRequests) {
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

    public boolean findUser(User user) {
        for (User u : users) {
            if (u.getUserID() == user.getUserID()) {
                return true;
            }
        }
        return false;
    }

    public boolean findItem(Item item) {
        for (Item i : items) {
            if (i.itemID == item.itemID) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> searchItems(String pattern, int numOfResults) {
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

    public ArrayList<User> searchUsers(String pattern, int numOfResults) {
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

    public ArrayList<Course> searchCourses(String pattern, int numOfResults) {
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
        Library libraryInstance = getInstance();
        int userId = generateRandomNumber();
        while (libraryInstance.noDupes.contains(userId)) {
            userId = generateRandomNumber();
        }
        return userId;
    }

    public static List<String> getUserRentedItemsWithDueDates(int userId) {
        List<String> rentedItemsInfo = new ArrayList<>();
        for (User user : getInstance().getUsers()) {
            if (user.getUserID() == userId) {
                user.getRentLog().forEach((item, dueDate) -> {
                    if (item.getItemType() == ItemType.BOOK) {
                        String info = String.format("Name: %s, Due Date: %s", item.getName(), dueDate.toString());
                        rentedItemsInfo.add(info);
                    }
                });
                break;
            }
        }
        return rentedItemsInfo;
    }

    public static Map<Course, Item> getCourseTextbooks() {
        return getInstance().course2textbook;
    }

    public static List<Item> checkForNewEditions(Course course) {
        List<Item> newerEditions = new ArrayList<>();
        Item currentTextbook = getCourseTextbooks().get(course);
        for (Item item : getInstance().getItems()) {
            if (item.getName().startsWith(currentTextbook.getName())
                    && !item.getName().equals(currentTextbook.getName())) {
                newerEditions.add(item);
            }
        }
        return newerEditions;
    }

    public static String getNotification() {
        StringBuilder notifications = new StringBuilder();
        for (Map.Entry<Course, Item> entry : getCourseTextbooks().entrySet()) {
            Course course = entry.getKey();
            Item textbook = entry.getValue();
            if (getInstance().getCopiesAvailable().getOrDefault(textbook, 0) <= 0) {
                if (notifications.length() > 0) {
                    notifications.append("\n");
                }
                notifications.append("Textbook for course ").append(course.getCourseName())
                        .append(" is not available.");
            }
        }
        return notifications.toString();
    }

    public HashMap<Item, Integer> getCopiesAvailable() {
        return new HashMap<>(copiesAvailable);
    }

    public HashMap<Course, Item> getCourse2textbook() {
        return new HashMap<>(course2textbook);
    }

    public HashMap<Item, Course> getTextbook2course() {
        return new HashMap<>(textbook2course);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : observers) {
            if (arg instanceof Item) {
                Item item = (Item) arg;
                ItemState state = item.getContext().getState();
                if (state instanceof AvailableState) {
                    observer.update("Item " + item.name + " is now available.");
                } else if (state instanceof CheckedOutState) {
                    observer.update("Item " + item.name + " has been checked out.");
                } else {
                    observer.update(arg);
                }
            }
        }
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public void addOverdueListener(OverdueListener listener) {
        overdueListeners.add(listener);
    }

    public void removeOverdueListener(OverdueListener listener) {
        overdueListeners.remove(listener);
    }

    public void notifyOverdueListeners(OverdueEvent event) {
        for (OverdueListener listener : overdueListeners) {
            listener.onOverdueItem(event);
        }
    }

    public synchronized void addUserSafely(User user) {
        addUser(user);
    }
}

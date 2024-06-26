package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private final int userID;
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private double accountBalance;
    // Added cartTotal for total cost for what user has in cart -Moses
    public double cartTotal;
    // Added choice for denial message, 1-4 for each cause. -Moses
    public int rentalDenied;
    private ArrayList<Course> courses;
    private ArrayList<Item> rentedItems;
    // Added userCart in order to store what user wants to buy -Moses
    public ArrayList<Item> userCart;
    private HashMap<Item, LocalDate> rentLog;

    private User(UserBuilder builder) {
        this.userID = builder.userID;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.userType = builder.userType;
        // Initialized in constructor but does not need any parameter addition -Moses
        this.cartTotal = 0.00;
        // Initialized in constuctor but does not need any parameter addition -Moses
        this.rentalDenied = 0;
        this.accountBalance = builder.accountBalance;
        this.courses = new ArrayList<>();
        this.rentedItems = new ArrayList<>();
        // Initialized in constructor but does not need any parameter addition -Moses
        this.userCart = new ArrayList<>();
        this.rentLog = new HashMap<>();
    }

    public static class UserBuilder {
        private final int userID;
        private final String name;
        private String email;
        private String password;
        private UserType userType;
        private double accountBalance = 0; // Default value

        public UserBuilder(int userID, String name) {
            this.userID = userID;
            this.name = name;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder userType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public UserBuilder accountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    // for adding items WITHOUT logging date. Used only when reconstructing the
    // objects.
    public boolean addItem(Item item) {
        if (item == null) {
            System.out.println("Attempted to add a null item.");
            return false;
        }

        if (threeOverDue())
            return false;

        if (!Library.getInstance().getCopiesAvailable().containsKey(item)) {
            System.out.println("Item doesnt exist");
            return false;
        }
        if (Library.getInstance().getCopiesAvailable().get(item) <= 0) {
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
            Library.getInstance().getCopiesAvailable().put(item,
                    Library.getInstance().getCopiesAvailable().get(item) - 1);
        }
        return true;
    }

    // for adding items WITH logging date. Used when renting new item.
    public boolean rentItem(Item item) {

        if (threeOverDue())
            return false;

        if (!Library.getInstance().getCopiesAvailable().containsKey(item)) {
            System.out.println("Item doesnt exist");
            return false;
        }
        if (Library.getInstance().getCopiesAvailable().get(item) <= 0) {
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
            Library.getInstance().getCopiesAvailable().put(item,
                    Library.getInstance().getCopiesAvailable().get(item) - 1);
        }
        this.accountBalance -= item.cost;
        return true;
    }

    public void returnItem(Item item) {
        this.accountBalance -= calcOverDueCharge4all(item);
        rentedItems.remove(item);
        rentLog.remove(item);
        if (item.itemType != ItemType.SUBSCRIPTION) {
            Library.getInstance().getCopiesAvailable().put(item,
                    Library.getInstance().getCopiesAvailable().get(item) + 1);
        }
    }

    public void addCourse(Course course) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(course.startDate) && currentDate.isBefore(course.endDate)) {
            courses.add(course);
            // System.out.println("Course added successfully.");
        } else {
            Item textbook = Library.getInstance().getCourse2textbook().get(course);
            System.out.println("Cannot add course. Current date is not between the start and end dates of the course.");
            if (textbook != null && rentedItems.contains(textbook)) {
                returnItem(textbook);
            }
        }
    }

    public void add2Balance(double amount) {
        accountBalance += amount;
    }

    public int daysOverdue(Item item) {
        LocalDate dueDate = rentLog.get(item);
        if (dueDate == null) {
            return 0;
        }
        long daysOverdue = LocalDate.now().toEpochDay() - dueDate.toEpochDay() - 30;
        daysOverdue = (daysOverdue < 0) ? 0 : daysOverdue;
        return (int) daysOverdue;
    }

    public void checkForOverdueItems() {
        for (Item item : rentedItems) {
            if (daysOverdue(item) > 0) {
                OverdueEvent event = new OverdueEvent(item, this);
                notifyOverdueListeners(event);
            }
        }
    }

    // Added add to cart for user to keep track of what user wants to buy -Moses
    public void addToCart(Item item) {
        this.userCart.add(item);
        this.cartTotal += item.cost;
    }

    private void notifyOverdueListeners(OverdueEvent event) {
        Library.getInstance().notifyOverdueListeners(event);
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
        return ((double) daysOverdue(item)) * 0.5;
    }

    @Override
    public String toString() {
        return String.format("(userID=%d,  name=%s,  email=%s,  password=%s,  userType=%s,  accountBalance=%f)", userID,
                name, email, password, userType, accountBalance);
    }

    public String csvFormat() {
        return String.format("%d,%s,%s,%s,%s,%f", userID, name, email, password, userType, accountBalance);
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setAccountBalance(double balance) {
        this.accountBalance = balance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public HashMap<Item, LocalDate> getRentLog() {
        return rentLog;
    }

    public ArrayList<Item> getRentedItems() {
        return rentedItems;
    }

    public void deductFromBalance(double amount) {
        this.accountBalance -= amount;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getAccountType() {
        return userType.toString();
    }

}

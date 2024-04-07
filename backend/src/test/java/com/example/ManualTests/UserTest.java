package com.example.manualTests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.example.Course;
import com.example.Item;
import com.example.ItemType;
import com.example.Library;
import com.example.LocationType;
import com.example.StatusType;
import com.example.User;
import com.example.UserType;

public class UserTest {

    /* Tests for User Builder Class */
    @Test
    public void userBuilder_Test1() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        Assert.assertEquals(name, testUser.getName());
        Assert.assertEquals(email, testUser.getEmail());
        Assert.assertEquals(password, testUser.getPassword());
        Assert.assertEquals(userType, testUser.getUserType());
        Assert.assertTrue(accountBalance == testUser.getAccountBalance());
        Assert.assertTrue(userID == testUser.getUserID());
    }

    @Test
    public void userBuilder_Test2() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        double expectedCartTotal = 0.00;
        int expectedRentalDenied = 0;
        ArrayList<Course> expectedCourses = new ArrayList<Course>();
        ArrayList<Item> expectedRentedItems = new ArrayList<Item>();
        HashMap<Item, LocalDate> expectedRentLog = new HashMap<Item, LocalDate>();

        Assert.assertTrue(expectedCartTotal == testUser.cartTotal);
        Assert.assertTrue(expectedRentalDenied == testUser.rentalDenied);
        Assert.assertEquals(expectedCourses, testUser.getCourses());
        Assert.assertEquals(expectedRentedItems, testUser.getRentedItems());
        Assert.assertEquals(expectedRentLog, testUser.getRentLog());
    }

    @Test
    public void userBuilder_Test3() {
        String name = null;
        String email = null;
        String password = null;
        UserType userType = null;
        double accountBalance = 0;
        int userID = 0;

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        Assert.assertEquals(name, testUser.getName());
        Assert.assertEquals(email, testUser.getEmail());
        Assert.assertEquals(password, testUser.getPassword());
        Assert.assertEquals(userType, testUser.getUserType());
        Assert.assertTrue(accountBalance == testUser.getAccountBalance());
        Assert.assertTrue(userID == testUser.getUserID());
    }

    /* Tests for addItem and rentItem methods */

    // Testing addItem method in user, and testing if the proper item gets rented
    @Test
    public void userAddItem_Test1() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 0.00, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();

        expectedRented.add(itemList.get(0));
        boolean actual = testUser.addItem(itemList.get(0));
        boolean expected = true;

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertEquals(expected, actual);
    }

    // Testing addItem method in user, testing when user does not have enough
    // balance to rent the item
    @Test
    public void userAddItem_Test2() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 9.99, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();
        boolean expected = false;

        boolean actual = testUser.addItem(itemList.get(0));

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertEquals(expected, actual);
    }

    // Testing addItem method in user, testing when user rents more than 10 items
    @Test
    public void userAddItem_Test3() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 0, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            expectedRented.add(testItem1);
            testUser.addItem(itemList.get(0));
        }

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertTrue(false == testUser.addItem(itemList.get(0)));
    }

    // Testing addItem method in user, testing when user rents an item with no
    // copies left
    @Test
    public void userAddItem_Test4() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> expectedRented = new ArrayList<>();

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertTrue(false == testUser.addItem(null));
    }

    // Testing addItem method for amount of copies of an Item (not working rn)
    @Test
    public void userAddItem_Test5() {
        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 1.00, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);
        testLibrary.getCopiesAvailable().put(testItem1, 20);

        User testUser = new User.UserBuilder(1, "David Green")
                .email("d.green@gmail.com")
                .password("hello123")
                .userType(UserType.STUDENT)
                .accountBalance(1000)
                .build();

        User testUser2 = new User.UserBuilder(2, "James Kaid")
                .email("j.kaid@gmail.com")
                .password("bonjour321")
                .userType(UserType.FACULTY)
                .accountBalance(1000)
                .build();

        for (int i = 0; i < 10; i++) {
            assertTrue(testUser.addItem(testItem1));
            assertTrue(testUser2.addItem(testItem1));
        }

        assertEquals(10, testUser.getRentedItems().size());
        assertEquals(10, testUser2.getRentedItems().size());

        assertEquals(20, testLibrary.getCopiesAvailable().get(testItem1).intValue());
    }

    // Testing rentItem method in user, and testing if the proper item gets rented
    @Test
    public void userRentItem_Test1() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 0.00, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();

        expectedRented.add(itemList.get(0));
        boolean actual = testUser.rentItem(itemList.get(0));
        boolean expected = true;

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertEquals(expected, actual);
    }

    // Testing rentItem method in user, testing when user does not have enough
    // balance to rent the item
    @Test
    public void userRentItem_Test2() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 9.99, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();
        boolean expected = false;

        boolean actual = testUser.rentItem(itemList.get(0));

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertEquals(expected, actual);
    }

    // Testing rentItem method in user, testing when user rents more than 10 items
    @Test
    public void userRentItem_Test3() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 0, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            expectedRented.add(testItem1);
            testUser.rentItem(itemList.get(0));
        }

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertTrue(false == testUser.rentItem(itemList.get(0)));
    }

    // Testing rentItem method in user, testing when user rents an item with no
    // copies left
    @Test
    public void userRentItem_Test4() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> expectedRented = new ArrayList<>();

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertTrue(false == testUser.rentItem(null));
    }

    // Testing rentItem method for amount of copies of an Item (not working rn)
    @Test
    public void userRentItem_Test5() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 1000;
        int userID = Library.generateNextUserId();

        String name2 = "James Kaid";
        String email2 = "j.kaid@gmail.com";
        String password2 = "bonjour321";
        UserType userType2 = UserType.FACULTY;
        double accountBalance2 = 1000;
        int userID2 = Library.generateNextUserId();

        String name3 = "Bobby Sandiemanie";
        String email3 = "b.sandie@gmail.com";
        String password3 = "hihihi123";
        UserType userType3 = UserType.NON_FACULTY;
        double accountBalance3 = 1000;
        int userID3 = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 1.00, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        User testUser2 = new User.UserBuilder(userID2, name2)
                .email(email2)
                .password(password2)
                .userType(userType2)
                .accountBalance(accountBalance2)
                .build();

        User testUser3 = new User.UserBuilder(userID3, name3)
                .email(email3)
                .password(password3)
                .userType(userType3)
                .accountBalance(accountBalance3)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            expectedRented.add(testItem1);
            testUser.rentItem(itemList.get(0));
            testUser2.rentItem(itemList.get(0));
        }

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertEquals(expectedRented, testUser2.getRentedItems());
    }

    /* Tests for returnItem method */

    // Testing returnItem method for one item
    @Test
    public void userReturnItem_Test1() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 0.00, StatusType.ACTIVE);

        testLibrary.addItem(testItem1);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();

        expectedRented.add(itemList.get(0));
        boolean actual = testUser.rentItem(itemList.get(0));
        boolean expected = true;

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertEquals(expected, actual);

        testUser.returnItem(itemList.get(0));
        expectedRented.remove(0);
        Assert.assertEquals(expectedRented, testUser.getRentedItems());
    }

    // Testing returnItem/rentItem method for all item types
    @Test
    public void userReturnItem_Test2() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 100;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 9.99, StatusType.ACTIVE);
        Item testItem2 = new Item(1, "Example CD", ItemType.CD, LocationType.SOUTH_SHELF, 5.99, StatusType.ACTIVE);
        Item testItem3 = new Item(2, "Example Magazine", ItemType.MAGAZINE, LocationType.WEST_SHELF, 6.99,
                StatusType.ACTIVE);
        Item testItem4 = new Item(3, "Example Textbook", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 8.99,
                StatusType.ACTIVE);
        Item testItem5 = new Item(4, "Example Subscription", ItemType.SUBSCRIPTION, LocationType.SOUTH_SHELF, 4.99,
                StatusType.ACTIVE);

        testLibrary.addItem(testItem1);
        testLibrary.addItem(testItem2);
        testLibrary.addItem(testItem3);
        testLibrary.addItem(testItem4);
        testLibrary.addItem(testItem5);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Item> itemList = testLibrary.getItems();

        ArrayList<Item> expectedRented = new ArrayList<>();

        expectedRented.add(itemList.get(0));
        expectedRented.add(itemList.get(1));
        expectedRented.add(itemList.get(2));
        expectedRented.add(itemList.get(3));
        expectedRented.add(itemList.get(4));
        boolean actual = testUser.rentItem(itemList.get(0));
        boolean actual2 = testUser.rentItem(itemList.get(1));
        boolean actual3 = testUser.rentItem(itemList.get(2));
        boolean actual4 = testUser.rentItem(itemList.get(3));
        boolean actual5 = testUser.rentItem(itemList.get(4));
        boolean expected = true;

        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, actual2);
        Assert.assertEquals(expected, actual3);
        Assert.assertEquals(expected, actual4);
        Assert.assertEquals(expected, actual5);

        testUser.returnItem(itemList.get(4));
        testUser.returnItem(itemList.get(3));
        testUser.returnItem(itemList.get(2));
        testUser.returnItem(itemList.get(1));
        testUser.returnItem(itemList.get(0));
        expectedRented.remove(4);
        expectedRented.remove(3);
        expectedRented.remove(2);
        expectedRented.remove(1);
        expectedRented.remove(0);
        Assert.assertEquals(expectedRented, testUser.getRentedItems());
        System.out.println(testUser.getAccountBalance());
        Assert.assertEquals("63.05", String.format("%.2f", testUser.getAccountBalance()));
    }

    /* Tests for addCourse method */

    // Testing adding course that has already ended
    @Test
    public void userAddCourse_Test1() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Course testCourse = new Course(0, "Intro to Example", "EECS XX01", LocalDate.of(2024, 01, 01),
                LocalDate.of(2025, 12, 24));

        testLibrary.addCourse(testCourse);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Course> courseList = testLibrary.getCourses();
        System.out.println(courseList);

        ArrayList<Course> expectedCourses = new ArrayList<>();

        expectedCourses.add(courseList.get(0));
        testUser.addCourse(courseList.get(0));

        Assert.assertEquals(expectedCourses, testUser.getCourses());
    }

    // Testing null courses added
    @Test
    public void userAddCourse_Test2() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();
        Course testCourse = new Course(0, "Intro to Example", "EECS XX01", LocalDate.now(),
                LocalDate.now().plusDays(30));

        testLibrary.addCourse(testCourse);

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        ArrayList<Course> courseList = testLibrary.getCourses();

        ArrayList<Course> expectedCourses = new ArrayList<>();
        testUser.addCourse(courseList.get(0));

        Assert.assertEquals(expectedCourses, testUser.getCourses());
    }

    /*
     * Testing setAccountBalance, add2Balance, and deductFromBalance method for user
     */

    @Test
    public void userAdd2Balance_Test1() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        Assert.assertEquals(name, testUser.getName());
        Assert.assertEquals(email, testUser.getEmail());
        Assert.assertEquals(password, testUser.getPassword());
        Assert.assertEquals(userType, testUser.getUserType());
        Assert.assertTrue(accountBalance == testUser.getAccountBalance());
        Assert.assertTrue(userID == testUser.getUserID());

        testUser.setAccountBalance(100.00);

        testUser.add2Balance(100.00);
        double expectedBalance = 200.00;
        System.out.println(testUser.getAccountBalance());
        Assert.assertTrue(expectedBalance == testUser.getAccountBalance());

        testUser.deductFromBalance(100.00);
        double expectedBalance2 = 100.00;
        Assert.assertTrue(expectedBalance2 == testUser.getAccountBalance());
    }

    // Testing add2Balance method with decimal places to balance
    @Test
    public void userAdd2Balance_Test2() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        Assert.assertEquals(name, testUser.getName());
        Assert.assertEquals(email, testUser.getEmail());
        Assert.assertEquals(password, testUser.getPassword());
        Assert.assertEquals(userType, testUser.getUserType());
        Assert.assertTrue(accountBalance == testUser.getAccountBalance());
        Assert.assertTrue(userID == testUser.getUserID());

        testUser.setAccountBalance(10.05);

        testUser.add2Balance(27.95);
        double expectedBalance = 38.00;
        Assert.assertTrue(expectedBalance == testUser.getAccountBalance());

        testUser.deductFromBalance(4.95);
        double expectedBalance2 = 33.05;
        Assert.assertTrue(expectedBalance2 == testUser.getAccountBalance());
    }

    /* Testing addToCart method for user */

    @Test
    public void userAddToCart_Test1() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        Assert.assertEquals(name, testUser.getName());
        Assert.assertEquals(email, testUser.getEmail());
        Assert.assertEquals(password, testUser.getPassword());
        Assert.assertEquals(userType, testUser.getUserType());
        Assert.assertTrue(accountBalance == testUser.getAccountBalance());
        Assert.assertTrue(userID == testUser.getUserID());

        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 9.99, StatusType.ACTIVE);
        testLibrary.addItem(testItem1);

        ArrayList<Item> itemList = testLibrary.getItems();

        testUser.addToCart(itemList.get(0));
        ArrayList<Item> expectedCart = new ArrayList<Item>();
        expectedCart.add(itemList.get(0));
        double expectedCartTotal = itemList.get(0).getcost();
        Assert.assertEquals(expectedCart, testUser.userCart);
        Assert.assertTrue(expectedCartTotal == testUser.cartTotal);
    }

    /* Testing checkForOverdueItems method for user */

    @Test
    public void userAddToCart_Test2() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        Assert.assertEquals(name, testUser.getName());
        Assert.assertEquals(email, testUser.getEmail());
        Assert.assertEquals(password, testUser.getPassword());
        Assert.assertEquals(userType, testUser.getUserType());
        Assert.assertTrue(accountBalance == testUser.getAccountBalance());
        Assert.assertTrue(userID == testUser.getUserID());

        Item testItem1 = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 9.99, StatusType.ACTIVE);
        testLibrary.addItem(testItem1);

        ArrayList<Item> itemList = testLibrary.getItems();

        testUser.addToCart(itemList.get(0));
        ArrayList<Item> expectedCart = new ArrayList<Item>();
        expectedCart.add(itemList.get(0));
        double expectedCartTotal = itemList.get(0).getcost();
        Assert.assertEquals(expectedCart, testUser.userCart);
        Assert.assertTrue(expectedCartTotal == testUser.cartTotal);
    }

    /* Testing string conversion methods for user */

    @Test
    public void userToStringCSVFormatgetAccountType_Test1() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        Library testLibrary = Library.newInstance();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        Assert.assertEquals(name, testUser.getName());
        Assert.assertEquals(email, testUser.getEmail());
        Assert.assertEquals(password, testUser.getPassword());
        Assert.assertEquals(userType, testUser.getUserType());
        Assert.assertTrue(accountBalance == testUser.getAccountBalance());
        Assert.assertTrue(userID == testUser.getUserID());

        String toString = testUser.toString();
        System.out.println(toString);
        String csvFormat = testUser.csvFormat();
        System.out.println(csvFormat);
        String accountType = testUser.getAccountType();
        String expectedAccountType = "STUDENT";
        String expectedString = String.format(
                "(userID=%d,  name=%s,  email=%s,  password=%s,  userType=%s,  accountBalance=%f)", userID,
                name, email, password, userType, accountBalance);
        String expectedFormat = String.format("%d,%s,%s,%s,%s,%f", userID, name, email, password, userType,
                accountBalance);
        ;

        Assert.assertEquals(expectedString, toString);
        Assert.assertEquals(expectedFormat, csvFormat);
        Assert.assertEquals(expectedAccountType, accountType);
    }
}

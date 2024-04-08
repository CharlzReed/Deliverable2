package com.example.manualTests;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.example.Course;
import com.example.Item;
import com.example.ItemType;
import com.example.Library;
import com.example.LocationType;
import com.example.Observer;
import com.example.OverdueEvent;
import com.example.OverdueListener;
import com.example.OverdueNotifier;
import com.example.StatusType;
import com.example.User;
import com.example.UserType;
import com.example.User.UserBuilder;


public class LibraryTest {
    private Library library;
  
    @Before
    public void setUp() {
        library = Library.createlibrary();

    }
    

    @Test
    //TESTING THE ADDING ITEMS AND BEING ABLE TO FIND THEM
    public void testAddItems() {
        Item item1 = new Item(3148, "The Alchemist", ItemType.BOOK, LocationType.NORTH_SHELF, 15.99, StatusType.ACTIVE);
        Item item2 = new Item(1014, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.ACTIVE);
        library.addItem(item1);
        library.addItem(item2);
        assertTrue(library.findItem(item1));
        assertTrue(library.findItem(item2));
    }

    @Test
    //TESTING THE REGISTER
    public void testRegisterNewUser() {
        library.registerNewUser(1001, "John Doe", "john@example.com", "password", UserType.STUDENT, 50.0);
        User user = new User.UserBuilder(1001, "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();
        assertTrue(library.findUser(user));
    }

    @Test
    //TESTING SEARCH FOR AN ITEM
    public void testSearchItems() {
        Item item1 = new Item(3148, "The Alchemist", ItemType.BOOK, LocationType.NORTH_SHELF, 15.99, StatusType.ACTIVE);
        Item item2 = new Item(1014, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.ACTIVE);
        library.addItem(item1);
        library.addItem(item2);

        ArrayList<Item> searchResults = library.searchItems("Alchemist", 5);
        assertEquals(1, searchResults.size());

        searchResults = library.searchItems("WWII", 5);
        assertEquals(1, searchResults.size());
    }

    @Test
    //BEING ABLE TO REIGSTER USERS AND FIND USERS
    public void testSearchUsers() {
        library.registerNewUser(1001, "John Doe", "john@example.com", "password", UserType.STUDENT, 50.0);
        library.registerNewUser(1002, "Jane Doe", "jane@example.com", "password", UserType.STUDENT, 60.0);

        ArrayList<User> searchResults = library.searchUsers("Doe", 5);
        assertEquals(2, searchResults.size());
    }

    @Test
    //BEING ABLE TO SEARCH FOR COURSES
    public void testSearchCourses() {
        Course course1 = new Course(1, "Math", "MATH101", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 5, 1));
        Course course2 = new Course(2, "Physics", "PHYS101", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 5, 1));
        library.addCourse(course1);
        library.addCourse(course2);

        ArrayList<Course> searchResults = library.searchCourses("Math", 5);
        assertEquals(1, searchResults.size());
    }
    @Test
    //TESTING THE ABILITY TO PROCCESS ITEMS THROUGH LIBRARY
    public void testProcessRequest() {
   
        Item item1 = new Item(3148, "The Alchemist", ItemType.BOOK, LocationType.NORTH_SHELF, 15.99, StatusType.PENDING_APPROVAL);
        Item item2 = new Item(1014, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        library.addItem(item1);
        library.addItem(item2);

        library.processRequest(2);

        assertEquals(item1.getstatustype(), StatusType.ACTIVE);
        assertEquals(item2.getstatustype(), StatusType.ACTIVE);
    }
    @Test
    //TESTING THE ASSIGNMENT OF COURSES TO TEXTBOOK
    public void testassigncoursetextbook(){

        Item item1 = new Item(1014, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        Course course1 = new Course(1, "History", "History101", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 5, 1));
        library.addCourse(course1);
        library.addItem(item1);
        library.processRequest(1);
        library.assign_course_textbook(course1, item1);
        assertTrue(library.getCourse2textbook().containsKey(course1)&& library.getTextbook2course().containsKey(item1));
    }
    @Test
    //TESTING THE ABILITY TO ADD COURSES
    public void testAddCourse() {
        Course course = new Course(3001, "Mathematics", "MATH101", LocalDate.now(), LocalDate.now().plusMonths(3));
        library.addCourse(course);
        assertTrue(library.getCourses().contains(course));
    }
   
    @Test
    //TESTING OBSERVERS
    public void testUpdate() {
    
        Observer observer = new Observer() {
            @Override
            public void update(Object arg) {
                assertEquals("Test", arg);
            }
        };
        observer.update("Test");
    }
    @Test
    //TESTING OBSERVERS
    public void testOnOverdueItem() {
       
        OverdueListener listener = new OverdueListener() {
            @Override
            public void onOverdueItem(OverdueEvent event) {
                assertNotNull(event);
                assertNotNull(event.getItem());
                assertNotNull(event.getUser());
            }
        };
        library.addOverdueListener(listener);
       
        Item item1 = new Item(1014, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        User user = new User.UserBuilder(1001, "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();

   
        OverdueEvent event = new OverdueEvent(item1, user);
        library.notifyOverdueListeners(event);
        assertEquals(item1, event.getItem());
        assertEquals(user, event.getUser());
    }
    @Test
    //TESTING REMOVING OVERDUELISTENER
    public void testOnremoveoverduelistener() {
       
        OverdueListener listener = new OverdueListener() {
            @Override
            public void onOverdueItem(OverdueEvent event) {
                assertNotNull(event);
                assertNotNull(event.getItem());
                assertNotNull(event.getUser());
            }
        };
        library.addOverdueListener(listener);
       
        Item item1 = new Item(1014, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        User user = new User.UserBuilder(1001, "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();

        library.removeOverdueListener(listener);
        assertTrue(!library.getOverduelistener().contains(listener));
        
    }
    @Test
    //TESTING THE OVERDUE NOTIFIER
    public void testOnOverdueItemNotifier() {
        OverdueNotifier notifier = new OverdueNotifier();
        Item item1 = new Item(1014, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        User user = new User.UserBuilder(1001, "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();
        OverdueEvent event = new OverdueEvent(item1, user);
        notifier.onOverdueItem(event);
    }
    @Test
    //TESTING  THE REQUEST ITEM
    public void testRequestItem(){
       
        boolean isinlib=false;
        library.requestItem("History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0);
        for (Item i:library.getItems()){
            if (i.getname().equals("History of WWII") && i.getItemType().equals(ItemType.TEXTBOOK)&& i.getlocationInLibrary().equals(LocationType.EAST_SHELF)&& i.getcost()==0){
                isinlib=true;
                break;
            }
        }
        assertTrue("Item not in Library",isinlib);
    }
    @Test  
    //testing the register observer
    public void Testregisterobserver(){
        
        Observer observer=new Observer() {
            @Override
            public void update(Object arg) {
                assertEquals("Item Mock Item is now available.", arg);
            }
        };
    
        library.registerObserver(observer);
        assertTrue(library.getObservers().contains(observer));
    }
    @Test  
    //testing the remove observer method
    public void Testremoveobserver(){
   
        Observer observer=new Observer() {
            @Override
            public void update(Object arg) {
                assertEquals("Item Mock Item is now available.", arg);
            }
        };
    
        library.registerObserver(observer);
        library.removeObserver(observer);
        assertTrue(!library.getObservers().contains(observer));
    }
    @Test
    //TESTING ADDING USERS
    public void Testaddusersafely(){
        User user = new User.UserBuilder(1005, "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();
        library.addUserSafely(user);
        assertTrue(library.getUsers().contains(user));

    }
    @Test
    //TESTING THE CEHCKOUT BOOK METHOD
    public void testusercheckoutbook(){
        User user = new User.UserBuilder(1003, "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();
        Item item1 = new Item(1018, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        library.addItem(item1);
        library.processRequest(1);
        library.addUserSafely(user);
        user.rentItem(item1);      
        assertTrue(user.getRentedItems().contains(item1));
    }
    
    @Test   
    //TESTING THE GETCOURSETEXTBOOK METHOD
    public void testgetcoursetextbook(){
        
        User user = new User.UserBuilder(library.generateNextUserId(), "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();
        Course course = new Course(3001, "Mathematics", "MATH101", LocalDate.now(), LocalDate.now().plusMonths(3));
        Item item1 = new Item(1014, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        library.addItem(item1);
        library.processRequest(1);
       
        library.addCourse(course);
        library.assign_course_textbook(course,item1);
        assertTrue(item1.getstatustype().equals(StatusType.ACTIVE));
        assertTrue(library.getCourseTextbooks().containsKey(course)&& library.getTextbook2course().containsKey(item1));

    }
    @Test
    //TESTING THE RETURN METHOD
    public void testuserreturnitem(){
        User user = new User.UserBuilder(1003, "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();
        Item item1 = new Item(1018, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        library.addItem(item1);
        library.processRequest(1);
        library.addUserSafely(user);
        user.rentItem(item1); 
        user.returnItem(item1);     
        assertTrue(!user.getRentedItems().contains(item1));
    }
    @Test
    //TESTING THE LIBRARY NEW EDITIONS
    public void testcheckfornewedition(){
        Course course = new Course(3001, "Mathematics", "MATH101", LocalDate.now(), LocalDate.now().plusMonths(3));
        Item item1 = new Item(1018, "History of WWII", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        Item newedition = new Item(1019, "History of WWII update", ItemType.TEXTBOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        library.addCourse(course);
        library.addItem(item1);
        library.processRequest(1);
        library.assign_course_textbook(course, item1);
        library.addItem(newedition);
        library.processRequest(1);

        assertTrue(library.checkForNewEditions(course).contains(newedition));
    }
    @Test
    //testing the function getuserrenteditemswithduedates
    public void testuserduedates(){
        User user = new User.UserBuilder(1234, "John Doe")
                .email("john@example.com")
                .password("password")
                .userType(UserType.STUDENT)
                .accountBalance(50.0)
                .build();
        library.addUserSafely(user);
        Item item1 = new Item(1018, "History of WWII", ItemType.BOOK, LocationType.EAST_SHELF, 0.0, StatusType.PENDING_APPROVAL);
        library.addItem(item1);
        library.processRequest(1);
        user.rentItem(item1); 
        LocalDate duedate= LocalDate.now().plusDays(7);
        user.getRentLog().put(item1,duedate);
        
        assertEquals((Library.getUserRentedItemsWithDueDates(user.getUserID())).toString(), "[Name: History of WWII, Due Date: 2024-04-15]");
       
       
    }
}

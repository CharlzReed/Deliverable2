package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ItemStateTests {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void availalbeTestCheckout() {
        Item book = new Book(1, "Effective Java", LocationType.NORTH_SHELF, 45.0, StatusType.ACTIVE, "Joshua Bloch", 2008);
        Item cd = new CD(2, "Thriller", LocationType.WEST_SHELF, 15.0, StatusType.ACTIVE, "Michael Jackson", 1982);
        Item magazine = new Magazine(3, "National Geographic", LocationType.SOUTH_SHELF, 5.0, StatusType.ACTIVE, "May 2021", "Science");
        Item subscription = new Subscription(4, "JSTOR", LocationType.EAST_SHELF, 100.0, StatusType.ACTIVE, "Academic Journal", "Monthly");
        
        ItemContext bookContext = new ItemContext(book);
        ItemContext cdContext = new ItemContext(cd);
        ItemContext magazineContext = new ItemContext(magazine);
        ItemContext subscriptionContext = new ItemContext(subscription);
        
        AvailableState availableState = new AvailableState();
        
        bookContext.setState(availableState);
        cdContext.setState(availableState);
        magazineContext.setState(availableState);
        subscriptionContext.setState(availableState);
        
        availableState.checkout(bookContext);
        availableState.checkout(cdContext);
        availableState.checkout(magazineContext);
        availableState.checkout(subscriptionContext);
        
        assertTrue("Book should be in CheckedOutState after checkout", bookContext.getState() instanceof CheckedOutState);
        assertTrue("CD should be in CheckedOutState after checkout", cdContext.getState() instanceof CheckedOutState);
        assertTrue("Magazine should be in CheckedOutState after checkout", magazineContext.getState() instanceof CheckedOutState);
        assertTrue("Subscription should be in CheckedOutState after checkout", subscriptionContext.getState() instanceof CheckedOutState);
    }

    @Test
    public void availableTestReturnItem() {
        Item book = new Book(1, "Effective Java", LocationType.NORTH_SHELF, 45.0, StatusType.ACTIVE, "Joshua Bloch", 2008);
        Item cd = new CD(2, "Thriller", LocationType.WEST_SHELF, 15.0, StatusType.ACTIVE, "Michael Jackson", 1982);
        Item magazine = new Magazine(3, "National Geographic", LocationType.SOUTH_SHELF, 5.0, StatusType.ACTIVE, "May 2021", "Science");
        Item subscription = new Subscription(4, "JSTOR", LocationType.EAST_SHELF, 100.0, StatusType.ACTIVE, "Academic Journal", "Monthly");
        
        ItemContext bookContext = new ItemContext(book);
        ItemContext cdContext = new ItemContext(cd);
        ItemContext magazineContext = new ItemContext(magazine);
        ItemContext subscriptionContext = new ItemContext(subscription);
        
        CheckedOutState checkedOutState = new CheckedOutState();
        
        bookContext.setState(checkedOutState);
        cdContext.setState(checkedOutState);
        magazineContext.setState(checkedOutState);
        subscriptionContext.setState(checkedOutState);
        
        checkedOutState.checkout(bookContext);
        checkedOutState.checkout(cdContext);
        checkedOutState.checkout(magazineContext);
        checkedOutState.checkout(subscriptionContext);
        
        assertTrue("Book should be in CheckedOutState after checkout", bookContext.getState() instanceof CheckedOutState);
        assertTrue("CD should be in CheckedOutState after checkout", cdContext.getState() instanceof CheckedOutState);
        assertTrue("Magazine should be in CheckedOutState after checkout", magazineContext.getState() instanceof CheckedOutState);
        assertTrue("Subscription should be in CheckedOutState after checkout", subscriptionContext.getState() instanceof CheckedOutState);
    }

    @Test
    public void checkedTestCheckout() {
        Item book = new Book(1, "Effective Java", LocationType.NORTH_SHELF, 45.0, StatusType.ACTIVE, "Joshua Bloch", 2008);
        ItemContext context = new ItemContext(book);
        CheckedOutState checkedOutState = new CheckedOutState();
        
        context.setState(checkedOutState);
        checkedOutState.checkout(context);
        
        assertTrue("The state should remain CheckedOutState after checkout attempt", context.getState() instanceof CheckedOutState);
    }

    @Test
    public void checkedTestReturnItem() {
        Item book = new Book(1, "Effective Java", LocationType.NORTH_SHELF, 45.0, StatusType.ACTIVE, "Joshua Bloch", 2008);
        Item cd = new CD(2, "Thriller", LocationType.WEST_SHELF, 15.0, StatusType.ACTIVE, "Michael Jackson", 1982);
        Item magazine = new Magazine(3, "National Geographic", LocationType.SOUTH_SHELF, 5.0, StatusType.ACTIVE, "May 2021", "Science");
        Item subscription = new Subscription(4, "JSTOR", LocationType.EAST_SHELF, 100.0, StatusType.ACTIVE, "Academic Journal", "Monthly");
        
        ItemContext bookContext = new ItemContext(book);
        ItemContext cdContext = new ItemContext(cd);
        ItemContext magazineContext = new ItemContext(magazine);
        ItemContext subscriptionContext = new ItemContext(subscription);
        
        CheckedOutState checkedOutState = new CheckedOutState();
        
        // Apply CheckedOutState and then return each item
        bookContext.setState(checkedOutState);
        cdContext.setState(checkedOutState);
        magazineContext.setState(checkedOutState);
        subscriptionContext.setState(checkedOutState);
        
        checkedOutState.returnItem(bookContext);
        checkedOutState.returnItem(cdContext);
        checkedOutState.returnItem(magazineContext);
        checkedOutState.returnItem(subscriptionContext);
        
        // Assert that all items transition back to AvailableState upon return
        assertTrue("Book should be in AvailableState after return", bookContext.getState() instanceof AvailableState);
        assertTrue("CD should be in AvailableState after return", cdContext.getState() instanceof AvailableState);
        assertTrue("Magazine should be in AvailableState after return", magazineContext.getState() instanceof AvailableState);
        assertTrue("Subscription should be in AvailableState after return", subscriptionContext.getState() instanceof AvailableState);
    }
}

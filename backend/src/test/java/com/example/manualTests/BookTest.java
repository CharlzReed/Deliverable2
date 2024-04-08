package com.example.manualTests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.example.Book;
import com.example.ItemFactory;
import com.example.LocationType;
import com.example.StatusType;

/**
 * Unit test for simple App.
 */
public class BookTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testBookConstructorAndGetters() {
        Book book = (Book) ItemFactory.createBook(1, "Effective Java", LocationType.NORTH_SHELF, 45.0,
                StatusType.ACTIVE, "Joshua Bloch", 2008);
        assertNotNull("Book instance should be created", book);
        assertEquals("Item ID should match constructor value", 1, book.itemID);
        assertEquals("Name should match constructor value", "Effective Java", book.getName());
        assertEquals("Location should match the constructor value", LocationType.NORTH_SHELF,
                book.getlocationInLibrary());
        assertEquals("Cost should match constructor value", 45.0, book.getcost(), 0.0);
        assertEquals("Status should match constructor value", StatusType.ACTIVE, book.getstatustype());
        assertEquals("Author should match constructor value", "Joshua Bloch", book.getAuthor());
        assertEquals("Publication year should match constructor value", 2008, book.getPublicationYear());
    }

    @Test
    public void testBookCsvFormat() {
        Book book = (Book) ItemFactory.createBook(1, "Effective Java", LocationType.NORTH_SHELF, 45.0,
                StatusType.ACTIVE, "Joshua Bloch", 2008);
        String expectedCsv = "1,Effective Java,BOOK,NORTH_SHELF,45.000000,ACTIVE,Joshua Bloch,2008";
        assertEquals("CSV output should match expected format", expectedCsv, book.csvFormat());
    }

    @Test
    public void testBookActiveState() {
        Book book = (Book) ItemFactory.createBook(1, "Effective Java", LocationType.NORTH_SHELF, 45.0,
                StatusType.ACTIVE, "Joshua Bloch", 2008);
        // Test behavior specific to the ACTIVE state
        assertTrue("Book should be available for checkout in ACTIVE state", book.isActive());
        assertTrue("Book should be available for checkout when available", book.isAvailable());
    }

    @Test
    public void testBookPendingApprovalState() {
        Book book = (Book) ItemFactory.createBook(1, "Effective Java", LocationType.NORTH_SHELF, 45.0,
                StatusType.PENDING_APPROVAL, "Joshua Bloch", 2008);
        // Test behavior specific to the PENDING_APPROVAL state
        assertFalse("Book should not be available for checkout in PENDING_APPROVAL state", book.isActive());
        assertFalse("Book should not be available for checkout when unavailable", book.isAvailable());
    }

    @Test
    public void testBookInactiveState() {
        Book book = (Book) ItemFactory.createBook(1, "Effective Java", LocationType.NORTH_SHELF, 45.0,
                StatusType.INACTIVE, "Joshua Bloch", 2008);
        // Test behavior specific to the INACTIVE state
        assertFalse("Book should not be available for checkout in INACTIVE state", book.isActive());
        assertFalse("Book should not be available for checkout when unavailable", book.isAvailable());

    }
}

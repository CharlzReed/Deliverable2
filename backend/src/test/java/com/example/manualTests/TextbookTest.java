package com.example.manualTests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import com.example.ItemFactory;
import com.example.LocationType;
import com.example.StatusType;
import com.example.Textbook;

/**
 * Unit test for simple App.
 */
public class TextbookTest {
    @Test
    public void textbookConstructorAndGetters() {
        // Assuming Textbook has similar properties to Book, and maybe additional ones
        // like subject
        Textbook textbook = (Textbook) ItemFactory.createTextbook(1, "Introduction to Algorithms",
                LocationType.NORTH_SHELF, 75.0,
                StatusType.ACTIVE, "Computer Science", "9780262033848");
        assertNotNull("Textbook instance should be created", textbook);
        assertEquals("Item ID should match constructor value", 1, textbook.itemID);
        assertEquals("Name should match constructor value", "Introduction to Algorithms", textbook.getName());
        assertEquals("Location should match the constructor value", LocationType.NORTH_SHELF,
                textbook.getlocationInLibrary());
        assertEquals("Cost should match constructor value", 75.0, textbook.getcost(), 0.0);
        assertEquals("Status should match constructor value", StatusType.ACTIVE, textbook.getstatustype());
        assertEquals("Subject should match constructor value", "Computer Science", textbook.getSubject());
    }

    @Test
    public void testTextbookCsvFormat() {
        Textbook textbook = (Textbook) ItemFactory.createTextbook(1, "Introduction to Algorithms",
                LocationType.NORTH_SHELF, 75.0,
                StatusType.ACTIVE, "Computer Science", "9780262033848");
        String expectedCsv = "1,Introduction to Algorithms,TEXTBOOK,NORTH_SHELF,75.000000,ACTIVE,Computer Science,9780262033848";
        assertEquals("CSV output should match expected format", expectedCsv, textbook.csvFormat());
    }

    @Test
    public void testTextbookActiveState() {
        Textbook textbook = (Textbook) ItemFactory.createTextbook(1, "Introduction to Algorithms",
                LocationType.NORTH_SHELF, 75.0,
                StatusType.ACTIVE, "Computer Science", "9780262033848");
        assertTrue("Textbook should be available for checkout in ACTIVE state", textbook.isActive());
        assertTrue("Textbook should be available for checkout when available", textbook.isAvailable());
    }

    @Test
    public void testTextbookPendingApprovalState() {
        Textbook textbook = (Textbook) ItemFactory.createTextbook(1, "Introduction to Algorithms",
                LocationType.NORTH_SHELF, 75.0,
                StatusType.PENDING_APPROVAL, "Computer Science", "9780262033848");
        assertFalse("Textbook should not be available for checkout in PENDING_APPROVAL state", textbook.isActive());
        assertFalse("Textbook should not be available for checkout when unavailable", textbook.isAvailable());
    }

    @Test
    public void testTextbookInactiveState() {
        Textbook textbook = (Textbook) ItemFactory.createTextbook(1, "Introduction to Algorithms",
                LocationType.NORTH_SHELF, 75.0,
                StatusType.INACTIVE, "Computer Science", "9780262033848");
        assertFalse("Textbook should not be available for checkout in INACTIVE state", textbook.isActive());
        assertFalse("Textbook should not be available for checkout when unavailable", textbook.isAvailable());
    }
}

package com.example;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
/**
 * Unit test for simple App.
 */
public class MagazineTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    void testMagazineConstructorAndGetters() {
        Magazine magazine = (Magazine) ItemFactory.createMagazine(3, "National Geographic", LocationType.SOUTH_SHELF, 5.0, StatusType.ACTIVE, "May 2021", "Science");
        assertNotNull("Magazine instance should be created", magazine);
        assertEquals("Item ID should match constructor value", 3, magazine.itemID);
        assertEquals("Name should match constructor value", "National Geographic", magazine.getName());
        assertEquals("Location should match constructor value", LocationType.SOUTH_SHELF, magazine.getlocationInLibrary());
        assertEquals("Cost should match constructor value", 5.0, magazine.getcost(), 0.0);
        assertEquals("Status should match constructor value", StatusType.ACTIVE, magazine.getstatustype());
        assertEquals("Issue number should match constructor value", "May 2021", magazine.getIssueNum());
        assertEquals("Genre should match constructor value", "Science", magazine.getGenre());
    }
}

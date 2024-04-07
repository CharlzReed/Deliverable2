package com.example;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
/**
 * Unit test for simple App.
 */
public class CDTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    void testCDConstructorAndGetters() {
        CD cd = (CD) ItemFactory.createCD(2, "Thriller", LocationType.WEST_SHELF, 15.0, StatusType.ACTIVE, "Michael Jackson", 1982);
        assertNotNull("CD instance should be created", cd);
        assertEquals("Item ID should match constructor value", 2, cd.itemID);
        assertEquals("Name should match constructor value", "Thriller", cd.getName());
        assertEquals("Location should match constructor value", LocationType.WEST_SHELF, cd.getlocationInLibrary());
        assertEquals("Cost should match constructor value", 15.0, cd.getcost(), 0.0);
        assertEquals("Status should match constructor value", StatusType.ACTIVE, cd.getstatustype());
        assertEquals("Artist should match constructor value", "Michael Jackson", cd.getArtist());
        assertEquals("Release year should match constructor value", 1982, cd.getReleaseYear());
    }
}

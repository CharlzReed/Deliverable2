package com.example.manualTests;

import org.junit.Test;

import com.example.CD;
import com.example.ItemFactory;
import com.example.LocationType;
import com.example.StatusType;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class CDTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testCDConstructorAndGetters() {
        CD cd = (CD) ItemFactory.createCD(2, "Thriller", LocationType.WEST_SHELF, 15.0, StatusType.ACTIVE,
                "Michael Jackson", 1982);
        assertNotNull("CD instance should be created", cd);
        assertEquals("Item ID should match constructor value", 2, cd.itemID);
        assertEquals("Name should match constructor value", "Thriller", cd.getName());
        assertEquals("Location should match constructor value", LocationType.WEST_SHELF, cd.getlocationInLibrary());
        assertEquals("Cost should match constructor value", 15.0, cd.getcost(), 0.0);
        assertEquals("Status should match constructor value", StatusType.ACTIVE, cd.getstatustype());
        assertEquals("Artist should match constructor value", "Michael Jackson", cd.getArtist());
        assertEquals("Release year should match constructor value", 1982, cd.getReleaseYear());
    }

    @Test
    public void testCDCsvFormat() {
        CD cd = (CD) ItemFactory.createCD(2, "Thriller", LocationType.WEST_SHELF, 15.0,
                StatusType.ACTIVE, "Michael Jackson", 1982);
        String expectedCsv = "2,Thriller,CD,WEST_SHELF,15.000000,ACTIVE,Michael Jackson,1982";
        assertEquals("CSV output should match expected format", expectedCsv, cd.csvFormat());
    }

    @Test
    public void testCDActiveState() {
        CD cd = (CD) ItemFactory.createCD(2, "Thriller", LocationType.WEST_SHELF, 15.0,
                StatusType.ACTIVE, "Michael Jackson", 1982);
        assertTrue("CD should be available for checkout in ACTIVE state", cd.isAvailable());
        assertTrue("CD should be available for checkout when available ", cd.isActive());
    }

    @Test
    public void testCDPendingApprovalState() {
        CD cd = (CD) ItemFactory.createCD(2, "Thriller", LocationType.WEST_SHELF, 15.0,
                StatusType.PENDING_APPROVAL, "Michael Jackson", 1982);
        assertFalse("CD should not be available for checkout in PENDING_APPROVAL state", cd.isAvailable());
        assertFalse("CD should not be available for checkout when unavailable ", cd.isActive());

    }

    @Test
    public void testCDInactiveState() {
        CD cd = (CD) ItemFactory.createCD(2, "Thriller", LocationType.WEST_SHELF, 15.0,
                StatusType.INACTIVE, "Michael Jackson", 1982);
        assertFalse("CD should not be available for checkout in INACTIVE state", cd.isAvailable());
        assertFalse("CD should not be available for checkout when unavailable ", cd.isActive());

    }
}

package com.example.manualTests;

import org.junit.Test;

import com.example.ItemFactory;
import com.example.LocationType;
import com.example.Magazine;
import com.example.StatusType;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class MagazineTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testMagazineConstructorAndGetters() {
        Magazine magazine = (Magazine) ItemFactory.createMagazine(3, "National Geographic", LocationType.SOUTH_SHELF,
                5.0, StatusType.ACTIVE, "May 2021", "Science");
        assertNotNull("Magazine instance should be created", magazine);
        assertEquals("Item ID should match constructor value", 3, magazine.itemID);
        assertEquals("Name should match constructor value", "National Geographic", magazine.getName());
        assertEquals("Location should match constructor value", LocationType.SOUTH_SHELF,
                magazine.getlocationInLibrary());
        assertEquals("Cost should match constructor value", 5.0, magazine.getcost(), 0.0);
        assertEquals("Status should match constructor value", StatusType.ACTIVE, magazine.getstatustype());
        assertEquals("Issue number should match constructor value", "May 2021", magazine.getIssueNum());
        assertEquals("Genre should match constructor value", "Science", magazine.getGenre());
    }

    @Test
    public void testMagazineCsvFormat() {
        Magazine magazine = (Magazine) ItemFactory.createMagazine(3, "National Geographic", LocationType.SOUTH_SHELF,
                5.0,
                StatusType.ACTIVE, "May 2021", "Science");
        String expectedCsv = "3,National Geographic,MAGAZINE,SOUTH_SHELF,5.000000,ACTIVE,May 2021,Science";
        assertEquals("CSV output should match expected format", expectedCsv, magazine.csvFormat());
    }

    @Test
    public void testMagazineActiveState() {
        Magazine magazine = (Magazine) ItemFactory.createMagazine(3, "National Geographic", LocationType.SOUTH_SHELF,
                5.0,
                StatusType.ACTIVE, "May 2021", "Science");
        assertTrue("Magazine should be available for checkout when available", magazine.isAvailable());
        assertTrue("Magazine should be available for checkout in ACTIVE state", magazine.isActive());

    }

    @Test
    public void testMagazinePendingApprovalState() {
        Magazine magazine = (Magazine) ItemFactory.createMagazine(3, "National Geographic", LocationType.SOUTH_SHELF,
                5.0,
                StatusType.PENDING_APPROVAL, "May 2021", "Science");
        assertFalse("Magazine should not be available for checkout when unavailable", magazine.isAvailable());
        assertFalse("Magazine should be unavailable for checkout in PENDING_APPROVAL state", magazine.isActive());
    }

    @Test
    public void testMagazineInactiveState() {
        Magazine magazine = (Magazine) ItemFactory.createMagazine(3, "National Geographic", LocationType.SOUTH_SHELF,
                5.0,
                StatusType.INACTIVE, "May 2021", "Science");
        assertFalse("Magazine should not be available for checkout when unavailable", magazine.isAvailable());
        assertFalse("Magazine should be unavailable for checkout in INACTIVE state", magazine.isActive());

    }
}

package com.example;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
/**
 * Unit test for simple App.
 */
public class SubscriptionTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    void testSubscriptionConstructorAndGetters() {
        Subscription subscription = (Subscription) ItemFactory.createSubscription(4, "JSTOR", LocationType.EAST_SHELF, 100.0, StatusType.ACTIVE, "Academic Journal", "Monthly");
        assertNotNull("Subscription instance should be created", subscription);
        assertEquals("Item ID should match constructor value", 4, subscription.itemID);
        assertEquals("Name should match constructor value", "JSTOR", subscription.getName());
        assertEquals("Location should match constructor value", LocationType.EAST_SHELF, subscription.getlocationInLibrary());
        assertEquals("Cost should match constructor value", 100.0, subscription.getcost(), 0.0);
        assertEquals("Status should match constructor value", StatusType.ACTIVE, subscription.getstatustype());
        assertEquals("Provider should match constructor value", "Academic Journal", subscription.getProvider());
        assertEquals("Subscription type should match constructor value", "Monthly", subscription.getSubType());
    }
}

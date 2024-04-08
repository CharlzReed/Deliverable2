package com.example.manualTests;

import org.junit.Test;

import com.example.ItemFactory;
import com.example.LocationType;
import com.example.StatusType;
import com.example.Subscription;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class SubscriptionTest {
        /**
         * Rigorous Test :-)
         */
        @Test
        public void testSubscriptionConstructorAndGetters() {
                Subscription subscription = (Subscription) ItemFactory.createSubscription(4, "JSTOR",
                                LocationType.EAST_SHELF,
                                100.0, StatusType.ACTIVE, "Academic Journal", "Monthly");
                assertNotNull("Subscription instance should be created", subscription);
                assertEquals("Item ID should match constructor value", 4, subscription.itemID);
                assertEquals("Name should match constructor value", "JSTOR", subscription.getName());
                assertEquals("Location should match constructor value", LocationType.EAST_SHELF,
                                subscription.getlocationInLibrary());
                assertEquals("Cost should match constructor value", 100.0, subscription.getcost(), 0.0);
                assertEquals("Status should match constructor value", StatusType.ACTIVE, subscription.getstatustype());
                assertEquals("Provider should match constructor value", "Academic Journal", subscription.getProvider());
                assertEquals("Subscription type should match constructor value", "Monthly", subscription.getSubType());
        }

        @Test
        public void testSubscriptionCsvFormat() {
                Subscription subscription = (Subscription) ItemFactory.createSubscription(4, "JSTOR",
                                LocationType.EAST_SHELF,
                                100.0,
                                StatusType.ACTIVE, "Academic Journal", "Monthly");
                String expectedCsv = "4,JSTOR,SUBSCRIPTION,EAST_SHELF,100.000000,ACTIVE,Academic Journal,Monthly";
                assertEquals("CSV output should match expected format", expectedCsv, subscription.csvFormat());
        }

        @Test
        public void testSubscriptionActiveState() {
                Subscription subscription = (Subscription) ItemFactory.createSubscription(4, "JSTOR",
                                LocationType.EAST_SHELF,
                                100.0,
                                StatusType.ACTIVE, "Academic Journal", "Monthly");
                assertTrue("Subscription should be available for access in ACTIVE state", subscription.isAvailable());
                assertTrue("Subscription should be available for checkout when available", subscription.isActive());
        }

        @Test
        public void testSubscriptionPendingApprovalState() {
                Subscription subscription = (Subscription) ItemFactory.createSubscription(4, "JSTOR",
                                LocationType.EAST_SHELF,
                                100.0,
                                StatusType.PENDING_APPROVAL, "Academic Journal", "Monthly");
                assertFalse("Subscription should not be available for access in PENDING_APPROVAL state",
                                subscription.isActive());
                assertFalse("Subscription should be unavailable for checkout when unavailable",
                                subscription.isAvailable());
        }

        @Test
        public void testSubscriptionInactiveState() {
                Subscription subscription = (Subscription) ItemFactory.createSubscription(4, "JSTOR",
                                LocationType.EAST_SHELF,
                                100.0,
                                StatusType.INACTIVE, "Academic Journal", "Monthly");
                assertFalse("Subscription should not be available for access in INACTIVE state",
                                subscription.isActive());
                assertFalse("Subscription should be unavailable for checkout when unavailable",
                                subscription.isAvailable());
        }
}

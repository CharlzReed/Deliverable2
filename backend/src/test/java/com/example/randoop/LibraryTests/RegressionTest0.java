package com.example.randoop.LibraryTests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        java.lang.String str0 = com.example.Library.getNotification();
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "" + "'", str0, "");
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        java.util.List<java.lang.String> strList1 = com.example.Library.getUserRentedItemsWithDueDates((int) (byte) 10);
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        com.example.Library library0 = com.example.Library.getInstance();
        com.example.OverdueListener overdueListener1 = null;
        library0.addOverdueListener(overdueListener1);
        java.util.HashMap<com.example.Item, com.example.Course> itemMap3 = library0.getTextbook2course();
        org.junit.Assert.assertNotNull(library0);
        org.junit.Assert.assertNotNull(itemMap3);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        com.example.Library library0 = com.example.Library.getInstance();
        java.util.ArrayList<com.example.User> userList1 = library0.getUsers();
        com.example.ItemType itemType3 = null;
        com.example.LocationType locationType4 = null;
        library0.requestItem("hi!", itemType3, locationType4, (double) (short) 10);
        org.junit.Assert.assertNotNull(library0);
        org.junit.Assert.assertNotNull(userList1);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        com.example.Library library0 = com.example.Library.getInstance();
        com.example.OverdueListener overdueListener1 = null;
        library0.addOverdueListener(overdueListener1);
        com.example.Item item3 = null;
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean4 = library0.findItem(item3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"itemID\" because \"item\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(library0);
    }
}


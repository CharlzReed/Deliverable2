package com.example.randoop.ItemTests;

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
        com.example.ItemType itemType2 = null;
        com.example.LocationType locationType3 = null;
        com.example.StatusType statusType5 = null;
        com.example.Item item6 = new com.example.Item(1, "hi!", itemType2, locationType3, (double) 100.0f, statusType5);
        java.lang.Class<?> wildcardClass7 = item6.getClass();
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        com.example.ItemType itemType2 = null;
        com.example.LocationType locationType3 = null;
        com.example.StatusType statusType5 = null;
        com.example.Item item6 = new com.example.Item(1, "hi!", itemType2, locationType3, (double) 100.0f, statusType5);
        java.lang.String str7 = item6.getName();
        com.example.ItemType itemType8 = item6.getItemType();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertNull(itemType8);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        com.example.ItemType itemType2 = null;
        com.example.LocationType locationType3 = null;
        com.example.StatusType statusType5 = null;
        com.example.Item item6 = new com.example.Item(1, "hi!", itemType2, locationType3, (double) 100.0f, statusType5);
        item6.cost = (byte) 100;
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        com.example.ItemType itemType2 = null;
        com.example.LocationType locationType3 = null;
        com.example.StatusType statusType5 = null;
        com.example.Item item6 = new com.example.Item((int) (byte) 0, "", itemType2, locationType3, (double) 10, statusType5);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        com.example.ItemType itemType2 = null;
        com.example.LocationType locationType3 = null;
        com.example.StatusType statusType5 = null;
        com.example.Item item6 = new com.example.Item(1, "hi!", itemType2, locationType3, (double) 100.0f, statusType5);
        java.lang.String str7 = item6.getName();
        boolean boolean8 = item6.isAvailable();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        com.example.ItemType itemType2 = null;
        com.example.LocationType locationType3 = null;
        com.example.StatusType statusType5 = null;
        com.example.Item item6 = new com.example.Item(1, "hi!", itemType2, locationType3, (double) 100.0f, statusType5);
        java.lang.String str7 = item6.getName();
        double double8 = item6.getCostAfterDiscount();
        com.example.DiscountStrategy discountStrategy9 = null;
        item6.applyDiscountStrategy(discountStrategy9);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + double8 + "' != '" + 100.0d + "'", double8 == 100.0d);
    }

    @Test
    public void test7() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test7");
        com.example.ItemType itemType2 = null;
        com.example.LocationType locationType3 = null;
        com.example.StatusType statusType5 = null;
        com.example.Item item6 = new com.example.Item(1, "hi!", itemType2, locationType3, (double) 100.0f, statusType5);
        java.lang.String str7 = item6.getName();
        java.lang.String str8 = item6.name;
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
    }
}


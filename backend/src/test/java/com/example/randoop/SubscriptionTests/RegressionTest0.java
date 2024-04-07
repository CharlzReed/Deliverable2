package com.example.randoop.SubscriptionTests;

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
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.Subscription subscription7 = new com.example.Subscription((-1), "", locationType2, (double) 10, statusType4, "hi!", "hi!");
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.Subscription subscription7 = new com.example.Subscription(0, "", locationType2, (double) (short) 100, statusType4, "", "");
        java.lang.String str8 = subscription7.getName();
        java.lang.String str9 = subscription7.getProvider();
        com.example.LocationType locationType10 = subscription7.getlocationInLibrary();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertNull(locationType10);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.Subscription subscription7 = new com.example.Subscription(0, "", locationType2, (double) (short) 100, statusType4, "", "");
        java.lang.String str8 = subscription7.getName();
        com.example.StatusType statusType9 = subscription7.getstatustype();
        com.example.LocationType locationType10 = subscription7.locationInLibrary;
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertNull(statusType9);
        org.junit.Assert.assertNull(locationType10);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.Subscription subscription7 = new com.example.Subscription(0, "", locationType2, (double) (short) 100, statusType4, "", "");
        java.lang.String str8 = subscription7.getName();
        java.lang.String str9 = subscription7.getProvider();
        double double10 = subscription7.getcost();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "" + "'", str9, "");
        org.junit.Assert.assertTrue("'" + double10 + "' != '" + 100.0d + "'", double10 == 100.0d);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.Subscription subscription7 = new com.example.Subscription(0, "", locationType2, (double) (short) 100, statusType4, "", "");
        java.lang.String str8 = subscription7.getEdition();
        java.lang.String str9 = subscription7.csvFormat();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "0,,SUBSCRIPTION,null,100.000000,null,," + "'", str9, "0,,SUBSCRIPTION,null,100.000000,null,,");
    }
}


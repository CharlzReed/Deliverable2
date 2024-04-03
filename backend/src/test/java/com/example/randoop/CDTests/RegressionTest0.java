package com.example.randoop.CDTests;

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
        com.example.CD cD7 = new com.example.CD((int) (byte) -1, "hi!", locationType2, (double) (-1L), statusType4, "", (int) (short) 10);
        com.example.LocationType locationType8 = null;
        cD7.locationInLibrary = locationType8;
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.CD cD7 = new com.example.CD((int) (byte) -1, "hi!", locationType2, (double) (-1L), statusType4, "", (int) (short) 10);
        java.lang.String str8 = cD7.getName();
        com.example.StatusType statusType9 = null;
        cD7.statusType = statusType9;
        boolean boolean11 = cD7.isSubscription();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.CD cD7 = new com.example.CD((int) (byte) -1, "hi!", locationType2, (double) (-1L), statusType4, "", (int) (short) 10);
        com.example.LocationType locationType8 = cD7.locationInLibrary;
        com.example.LocationType locationType9 = null;
        cD7.locationInLibrary = locationType9;
        org.junit.Assert.assertNull(locationType8);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.CD cD7 = new com.example.CD((int) (byte) -1, "hi!", locationType2, (double) (-1L), statusType4, "", (int) (short) 10);
        java.lang.String str8 = cD7.getName();
        java.lang.String str9 = cD7.csvFormat();
        com.example.LocationType locationType10 = cD7.locationInLibrary;
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "-1,hi!,CD,null,-1.000000,null,,10" + "'", str9, "-1,hi!,CD,null,-1.000000,null,,10");
        org.junit.Assert.assertNull(locationType10);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.CD cD7 = new com.example.CD((int) (byte) -1, "hi!", locationType2, (double) (-1L), statusType4, "", (int) (short) 10);
        java.lang.String str8 = cD7.getName();
        com.example.StatusType statusType9 = null;
        cD7.statusType = statusType9;
        java.lang.String str11 = cD7.csvFormat();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "-1,hi!,CD,null,-1.000000,null,,10" + "'", str11, "-1,hi!,CD,null,-1.000000,null,,10");
    }
}


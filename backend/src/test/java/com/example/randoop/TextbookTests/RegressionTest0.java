package com.example.randoop.TextbookTests;

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
        com.example.Textbook textbook7 = new com.example.Textbook((-1), "", locationType2, (double) 10, statusType4, "hi!", "hi!");
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        com.example.LocationType locationType2 = null;
        com.example.StatusType statusType4 = null;
        com.example.Textbook textbook7 = new com.example.Textbook(0, "", locationType2, (double) (short) 100, statusType4, "", "");
        java.lang.String str8 = textbook7.getName();
        java.lang.String str9 = textbook7.getISBN();
        com.example.LocationType locationType10 = textbook7.getlocationInLibrary();
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
        com.example.Textbook textbook7 = new com.example.Textbook(0, "", locationType2, (double) (short) 100, statusType4, "", "");
        java.lang.String str8 = textbook7.getName();
        com.example.StatusType statusType9 = textbook7.getstatustype();
        com.example.LocationType locationType10 = textbook7.locationInLibrary;
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
        com.example.Textbook textbook7 = new com.example.Textbook(0, "", locationType2, (double) (short) 100, statusType4, "", "");
        java.lang.String str8 = textbook7.getName();
        java.lang.String str9 = textbook7.getISBN();
        double double10 = textbook7.getcost();
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
        com.example.Textbook textbook7 = new com.example.Textbook(0, "", locationType2, (double) (short) 100, statusType4, "", "");
        java.lang.String str8 = textbook7.getEdition();
        java.lang.String str9 = textbook7.csvFormat();
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "0,,TEXTBOOK,null,100.000000,null,," + "'", str9, "0,,TEXTBOOK,null,100.000000,null,,");
    }
}


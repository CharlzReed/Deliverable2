package com.example.randoop.UserTests;

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
        com.example.User.UserBuilder userBuilder2 = new com.example.User.UserBuilder((-1), "");
        com.example.User user3 = userBuilder2.build();
        com.example.UserType userType4 = user3.getUserType();
        com.example.Item item5 = null;
        // The following exception was thrown during execution in test generation
        try {
            user3.addToCart(item5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"cost\" because \"item\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertNull(userType4);
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        com.example.User.UserBuilder userBuilder2 = new com.example.User.UserBuilder((int) (short) 100, "hi!");
        com.example.User user3 = userBuilder2.build();
        org.junit.Assert.assertNotNull(user3);
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        com.example.User.UserBuilder userBuilder2 = new com.example.User.UserBuilder((-1), "");
        com.example.User user3 = userBuilder2.build();
        com.example.User.UserBuilder userBuilder5 = userBuilder2.email("");
        org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertNotNull(userBuilder5);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        com.example.User.UserBuilder userBuilder2 = new com.example.User.UserBuilder((-1), "");
        com.example.User user3 = userBuilder2.build();
        com.example.Item item4 = null;
        // The following exception was thrown during execution in test generation
        try {
            user3.returnItem(item4);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"itemType\" because \"item\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(user3);
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        com.example.User.UserBuilder userBuilder2 = new com.example.User.UserBuilder((int) (short) 100, "hi!");
        com.example.User.UserBuilder userBuilder4 = userBuilder2.email("");
        org.junit.Assert.assertNotNull(userBuilder4);
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        com.example.User.UserBuilder userBuilder2 = new com.example.User.UserBuilder((-1), "");
        com.example.User user3 = userBuilder2.build();
        java.lang.String str4 = user3.getPassword();
        org.junit.Assert.assertNotNull(user3);
        org.junit.Assert.assertNull(str4);
    }
}


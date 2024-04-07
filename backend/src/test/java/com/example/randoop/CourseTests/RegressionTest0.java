package com.example.randoop.CourseTests;

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
        java.time.LocalDate localDate3 = null;
        java.time.LocalDate localDate4 = null;
        com.example.Course course5 = new com.example.Course((int) (short) 1, "", "hi!", localDate3, localDate4);
        course5.courseCode = "hi!";
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = course5.csvFormat();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.time.LocalDate.toString()\" because \"this.startDate\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        java.time.LocalDate localDate3 = null;
        java.time.LocalDate localDate4 = null;
        com.example.Course course5 = new com.example.Course((int) (byte) 10, "", "", localDate3, localDate4);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = course5.toString();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.time.LocalDate.toString()\" because \"this.startDate\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        java.time.LocalDate localDate3 = null;
        java.time.LocalDate localDate4 = null;
        com.example.Course course5 = new com.example.Course((int) (byte) 10, "", "", localDate3, localDate4);
        java.lang.Class<?> wildcardClass6 = course5.getClass();
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        java.time.LocalDate localDate3 = null;
        java.time.LocalDate localDate4 = null;
        com.example.Course course5 = new com.example.Course((int) (byte) 10, "", "", localDate3, localDate4);
        java.time.LocalDate localDate6 = null;
        course5.endDate = localDate6;
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        java.time.LocalDate localDate3 = null;
        java.time.LocalDate localDate4 = null;
        com.example.Course course5 = new com.example.Course((int) (short) 1, "", "hi!", localDate3, localDate4);
        course5.courseCode = "hi!";
        course5.courseCode = "hi!";
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        java.time.LocalDate localDate3 = null;
        java.time.LocalDate localDate4 = null;
        com.example.Course course5 = new com.example.Course((int) (short) 1, "", "hi!", localDate3, localDate4);
        course5.courseCode = "hi!";
        java.lang.String str8 = course5.courseName;
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test7() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test7");
        java.time.LocalDate localDate3 = null;
        java.time.LocalDate localDate4 = null;
        com.example.Course course5 = new com.example.Course((int) (byte) 10, "", "", localDate3, localDate4);
        int int6 = course5.courseID;
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
    }
}


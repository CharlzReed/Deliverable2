package com.example.manualTests;

import com.example.*;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class CourseTest {
	@Test
    public void userBuilder_Test1() {
		int courseID = 0;
		String courseName = "Intro to Example";
		String courseCode = "EECSXXXX";
		LocalDate start = LocalDate.of(2024, 04, 01);
		LocalDate end = LocalDate.of(2025, 04, 01);
        Course testCourse = new Course(courseID, courseName, courseCode, 
        		start, end);
        
        Assert.assertEquals(courseName, testCourse.getCourseName());
        
        String csvFormat = testCourse.csvFormat();
        String expectedCSVFormat = String.format("%s,%s,%s,%s,%s", String.valueOf(courseID), courseName, 
        		courseCode, start, end);
        Assert.assertEquals(expectedCSVFormat, csvFormat);

    }
}

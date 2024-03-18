package com.example;

import java.time.LocalDate;


public class Course {

    public int courseID;
    public String courseName;
    public String courseCode;
    public LocalDate startDate;
    public LocalDate endDate;

    public Course(int courseID, String courseName, String courseCode, LocalDate startDate, LocalDate endDate) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("(courseID=%s,  courseName=%s,  courseCode=%s,  startDate=%s,  endDate=%s)", String.valueOf(courseID), courseName, courseCode, startDate.toString(), endDate.toString());
    }
    
    public String csvFormat() {
        return String.format("%s,%s,%s,%s,%s", String.valueOf(courseID), courseName, courseCode, startDate.toString(), endDate.toString());
    }
    

}
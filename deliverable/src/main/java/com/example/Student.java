package com.example;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private int studentID;
    private List<Course> courses;
    private List<Textbook> textbooks;

    public Student(int userID, String userName, String email, String userType, boolean isVerified, int studentID) {
        super(userID, userName, email, UserType.STUDENT, isVerified);
        this.studentID = studentID;
        this.courses = new ArrayList<>();
        this.textbooks = new ArrayList<>();
    }

    public Textbook getTextbook(Textbook tb) {
        for (Textbook t : textbooks) {
            if (t.equals(tb)) {
                return tb;
            }
        }
        return null;
    }

    public boolean removeTextbook(Textbook tb) {
        return textbooks.remove(tb);
    }

    public boolean addTextbook(Textbook tb) {
        if (!textbooks.contains(tb)) {
            textbooks.add(tb);
            return true;
        }
        return false;
    }

    // prob have to change
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public List<Textbook> getTextbooks() {
        return textbooks;
    }

}

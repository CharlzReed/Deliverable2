package com.example;

import java.util.List;

import java.util.ArrayList;

public class Faculty extends User {
    private List<Course> courses;

    public Faculty(int userID, String userName, String email, UserType userType, boolean isVerified) {
        super(userID, userName, email, UserType.FACULTY, isVerified);
        this.courses = new ArrayList<>();
    }

    public Course getCourse(Course course) {
        for (Course c : courses) {
            if (c.equals(course)) {
                return c;
            }
        }
        return null;
    }

    public List<Textbook> getCourseTextbooks() {
        List<Textbook> textbooks = new ArrayList<>();
        for (Course c : courses) {
            textbooks.addAll(c.getTextbooks());
        }
        return textbooks;
    }

    public void notifyNewEdition(Textbook textbook) {
        // notify user somehow
    }

    public void updateCourseTextbook(Course course, Textbook newEdition) {
        // update specified course textbook
    }
}

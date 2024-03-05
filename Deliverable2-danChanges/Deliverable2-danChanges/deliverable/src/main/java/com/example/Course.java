package com.example;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseID;
    private String title;
    private List<Textbook> textbooks;

    public Course(int courseID, String title) {
        this.courseID = courseID;
        this.title = title;
        this.textbooks = new ArrayList<>();
    }

    public void addTextbook(Textbook textbook) {
        if (textbook != null && !textbooks.contains(textbook)) {
            textbooks.add(textbook);
        }
    }

    public boolean removeTextbook(Textbook textbook) {
        return textbooks.remove(textbook);
    }

    public List<Textbook> getTextbooks() {
        return new ArrayList<>(textbooks);
    }

    // Getters
    public int getCourseID() {
        return courseID;
    }

    public String getTitle() {
        return title;
    }

    // Setters
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void updateTextbookEdition(Textbook textbook, String newEdition, User user) {
        for (Textbook tb : textbooks) {
            if (tb.equals(textbook)) {
                tb.setEdition(newEdition, user);
            }
        }
    }
}
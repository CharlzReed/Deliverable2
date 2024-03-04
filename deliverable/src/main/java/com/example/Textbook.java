package com.example;


public class Textbook extends Item {
    private int courseID;
    private String ISBN;
    private String edition;

    public Textbook(int itemID, String title, int copiesAvailable, boolean canBePurchased, int courseID, String ISBN,
            String edition) {
        super(itemID, title, copiesAvailable, canBePurchased);
        this.courseID = courseID;
        this.ISBN = ISBN;
        this.edition = edition;
    }

    public boolean checkAvailability(Textbook tb) {
        return tb.getCanBePurchased();
    }

    // Getters
    public String getISBN() {
        return this.ISBN;
    }

    public String getEdition() {
        return this.edition;
    }

    public int getCourseID() {
        return this.courseID;
    }

    // Setters

    protected void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    protected void setEdition(String edition, User user) {
        if (user.getUserType().equals("admin")) {
            this.edition = edition;
        } else {
            throw new SecurityException("Only admin users can update the edition.");
        }
    }

}

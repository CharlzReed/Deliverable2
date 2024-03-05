package com.example;

import java.util.ArrayList;
import java.util.List;

public class Textbook extends Book {
    private int courseID;
    private String edition;
    private ArrayList<TextbookRequest> textbookRequests;

    public Textbook(String title, int copiesAvailable, String author, boolean canBePurchased, int courseID, String ISBN,
            String edition, ItemType itemType, double price) {
        super(title, copiesAvailable, canBePurchased, author, ISBN, ItemType.TEXTBOOK, price);
        this.courseID = courseID;
        this.edition = edition;
        this.textbookRequests= new ArrayList<>();
    }

    public boolean checkAvailability(Textbook tb) {
        return tb.getCanBePurchased();
    }

    // Getters

    public String getEdition() {
        return this.edition;
    }

    public int getCourseID() {
        return this.courseID;
    }
    
    public void addTextbookRequest(TextbookRequest t){
        this.textbookRequests.add(t);
    }

    // Setters

    protected void setEdition(String edition, User user) {
        if (user.getUserType().equals(user.getUserType())) {
            this.edition = edition;
        } else {
            throw new SecurityException("Only admin users can update the edition.");
        }
    }

    @Override
    public String toStringCSV() {
        // Introduction to Algorithms,7,Thomas H.
        // Cormen,true,TEXTBOOK,80.99,23132131,3rd,0,

        String message = this.title + "," + this.copiesAvailable + "," + this.getAuthor() + "," + this.canBePurchased
                + ",TEXTBOOK," + this.price + "," +
                this.getISBN() + "," + this.edition + "," + this.courseID + ",";

        return message;
    }

}

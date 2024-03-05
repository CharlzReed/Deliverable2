package com.example;

public class TextbookRequest {
    private String ISBN;
    private static int requestPriority = 0; // Initialize to 0
    private User user;

    // Assuming ISBN should be an integer, fixed constructor visibility and
    // parameters
    public TextbookRequest(String title, int courseID, int ISBN, User user) {
        this.ISBN = ISBN;
        TextbookRequest.requestPriority++;
        this.user = user;
    }

    // Static method to request a textbook, assuming this method should be in
    // another class
    public static void requestTextbook(String title, int courseID, String ISBN, String edition, User user) {

        // Check if the textbook already exists in the database
        boolean exists = false;
        for (Textbook textbook : Database.textBookDatabase) {
            if (textbook.getISBN().equals(ISBN)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            Textbook newBook = new Textbook(title, courseID, ISBN, edition, ItemType.TEXTBOOK, 0);
            TextbookRequest request = new TextbookRequest(title, courseID, isbnInt, user);
            newBook.addTextbookRequest(request);
            Database.textBookDatabase.add(newBook);
        }
    }

    // we change to send to UI but for now print
    public void notifyUser() {
        System.out.println("Notification: ");
    }
}

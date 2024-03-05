package com.example;

public class TextbookRequest {
    private int ISBN;
    private static int requestPriority;
    private User user;
    
    private TextbookRequest(int ISBN, int requestPriority, User user) {
        this.ISBN = ISBN;
        this.requestPriority++;
        this.user = user;
    }

    public TextbookRequest requestTextbook(String title, int courseID, String ISBN, String edition){
        if()
    }



    //we change to send to UI but for now print
    public void notifyUser(){
        System.out.println("Notification: ");
    }
}

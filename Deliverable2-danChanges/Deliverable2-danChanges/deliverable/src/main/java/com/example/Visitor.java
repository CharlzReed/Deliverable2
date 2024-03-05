package com.example;

public class Visitor extends User {
    public Visitor(int userID, String userName, String email, UserType userType, boolean isVerified) {
        super(userID, userName, email, UserType.VISITOR, isVerified);
    }
}

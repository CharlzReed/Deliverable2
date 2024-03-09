package com.example;

public class NonFaculty extends User {
    public NonFaculty(int userID, String userName, String email, UserType userType, boolean isVerified, double accountBalance) {
        super(userID, userName, email, UserType.NON_FACULTY, isVerified, accountBalance);
    }
}

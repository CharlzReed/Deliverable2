package com.example;

public class SystemAdmin extends User {

    public SystemAdmin(int userID, String userName, String email, UserType userType, boolean isVerified, double accountBalance) {
        super(userID, userName, email, UserType.ADMIN, isVerified, accountBalance);
    }

}

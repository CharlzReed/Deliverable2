package com.example;

import java.util.HashMap;
import java.util.Map;

public abstract class User {
    private int userID;
    private String userName;
    private String email;
    private String password;
    private UserType userType;
    private boolean isVerified;

    // this is just here to act as the database
    private static Map<String, String> userDatabase = new HashMap<>();

    public User(int userID, String userName, String email, UserType userType, boolean isVerified) {
        this.userID = userID;
        this.userName = userName;
        this.userType = userType;
        this.email = email;
        this.isVerified = isVerified;
    }

    public boolean login(String userN, String password) {
        String storedPass = userDatabase.get(userN);
        return storedPass != null && checkPassword(userN, password);
    }

    public void register(String userN, String pass) {
        if (userDatabase.containsKey(userN)) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        userDatabase.put(userN, password);
    }

    protected boolean checkPassword(String userN, String enteredPass) {
        String storedPass = userDatabase.get(userN);
        return storedPass.equals(enteredPass);
    }

/*  private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            BigInteger number = new BigInteger(1, hashedBytes);
            StringBuilder hexString = new StringBuilder(number.toString(16));

            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
    protected String getUserName() {
        return this.userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isVerified() {
        return isVerified;
    }

}


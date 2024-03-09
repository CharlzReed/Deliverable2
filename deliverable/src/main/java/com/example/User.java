package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class User {
    private int userID;
    private String userName;
    private String email;
    private String password;
    private UserType userType;
    private boolean isVerified;
    private PaymentTypes paymentType;
    private double accountBalance;

    // this is just here to act as the database
    private static Map<String, String> userDatabase = new HashMap<>();
    public ArrayList<Item> currentlyRented;

    public User(int userID, String userName, String email, UserType userType, boolean isVerified, double accountBalance) {
        this.userID = userID;
        this.userName = userName;
        this.userType = userType;
        this.email = email;
        this.isVerified = isVerified;
        this.accountBalance = accountBalance;
        this.currentlyRented = new ArrayList<>();
    }

    public void rentItem(Item item) {
        currentlyRented.add(item);
    }

    public ArrayList<Item> getCurrentlyRented_ALL() {
        return currentlyRented;
    }

    public ArrayList<Item> getCurrentlyRented_TYPE(ItemType itemType) {
        ArrayList<Item> temp = new ArrayList<>();
        for (int i = 0; i < currentlyRented.size(); i++) {
            if (currentlyRented.get(i).itemType == itemType) {
                temp.add(currentlyRented.get(i));
            }
        }
        return temp;
    }

    public String toStringCSV() {
        //userID,userName,email,userType,isVerified
        String msg = this.userID +","+ this.userName +","+ this.email +","+ this.userType +","+ this.isVerified +","+ this.accountBalance;
        return msg;
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

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public PaymentTypes getPaymentType() {
        return paymentType;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    // just for testing
    public void setVerification(boolean status) {
        this.isVerified = status;
    }

}


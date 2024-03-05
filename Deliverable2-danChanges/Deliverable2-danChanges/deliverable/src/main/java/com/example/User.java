package com.example;

import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

public abstract class User {
    private int userID;
    private String userName;
    private String email;
    private String password;
    private UserType userType;
    private boolean isVerified;

    public User(int userID, String userName, String email, UserType userType, boolean isVerified) {
        this.userID = userID;
        this.userName = userName;
        this.userType = userType;
        this.email = email;
        this.isVerified = isVerified;
    }

    public String toStringCSV() {
        // userID,userName,email,userType,isVerified
        String msg = this.userID + "," + this.userName + "," + this.email + "," + this.userType + "," + this.isVerified;
        return msg;
    }

    public boolean login(String userN, String password) {
        switch(UserType){
            case "STUDENT":
                if(!Database.studentDatabase.get(userN).equals(null)){
                    return checkPassword(userN, password);
                }
                break;

            case "FACULTY":
            if(!Database.facultyDatabase.get(userN).equals(null)){
                return checkPassword(userN, password);
            }
            break;
                break;
                
            case "NON-FACULTY":
                if(!Database.nonFacultyDatabase.get(userN).equals(null)){
                    return checkPassword(userN, password);
                }
                break;
                
            case "VISITOR":
                if(!Database.visitorDatabase.get(userN).equals(null)){
                    return checkPassword(userN, password);
                }
                break;
                
            case "ADMIN":
                if(!Database.adminDatabase.get(userN).equals(null)){
                    return checkPassword(userN, password);
                }
                break;
            }
        }

    public void register(String userN, String pass) {
        switch(UserType){
            case "STUDENT":
                if(Database.studentDatabase.get(userN).equals(null)){
                     checkPassword(userN, password);
                }
                break;

            case "FACULTY":
                if(!Database.facultyDatabase.get(userN).equals(null)){
                    return checkPassword(userN, password);
                }
                break;
                
            case "NON-FACULTY":
                if(!Database.nonFacultyDatabase.get(userN).equals(null)){
                    return checkPassword(userN, password);
                }
                break;
                
            case "VISITOR":
                if(!Database.visitorDatabase.get(userN).equals(null)){
                    return checkPassword(userN, password);
                }
                break;
                
            case "ADMIN":
                if(!Database.adminDatabase.get(userN).equals(null)){
                    return checkPassword(userN, password);
                }
                break;
            }
        }

    protected boolean checkPassword(String userN, String enteredPass) {
        String storedPass = userDatabase.get(userN);
        return storedPass.equals(enteredPass);
    }

    /*
     * private String hashPassword(String password) {
     * try {
     * MessageDigest md = MessageDigest.getInstance("SHA-256");
     * byte[] hashedBytes = md.digest(password.getBytes());
     * 
     * BigInteger number = new BigInteger(1, hashedBytes);
     * StringBuilder hexString = new StringBuilder(number.toString(16));
     * 
     * while (hexString.length() < 32) {
     * hexString.insert(0, '0');
     * }
     * 
     * return hexString.toString();
     * } catch (NoSuchAlgorithmException e) {
     * e.printStackTrace();
     * }
     * return null;
     * }
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

    // just for testing
    public void setVerification(boolean status) {
        this.isVerified = status;
    }

    protected void setPassword(String newPassword){
        this.password = newPassword;
    }

}

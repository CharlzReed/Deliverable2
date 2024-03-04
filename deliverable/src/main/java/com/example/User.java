package com.example;

public interface User {

    public int userID = -1;
    public String name = "";
    public String email = "";
    public String password = "";
    public String userType = "";
    public boolean isVerified = false;


    public void login();

    public void register();

}


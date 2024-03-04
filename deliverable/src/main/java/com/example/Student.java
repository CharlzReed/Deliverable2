package com.example;

import java.util.List;

public class Student implements User {

    public int studentID;
    public List<String> courses;
    public List<String> textbooks;


    @Override
    public void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public void register() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

}

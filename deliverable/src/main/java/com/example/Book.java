package com.example;

public class Book implements Item {
    
    public String author;
    public String ISBN;

    @Override
    public void checkOut() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkOut'");
    }
    @Override
    public void returnItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnItem'");
    }
}

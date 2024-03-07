package com.example;

public class IdGenerator {
    private static int id = 1000;
    public static int getID() {
        return id++;
    }
}

package com.example;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double cost){
        return cost;
    }
}

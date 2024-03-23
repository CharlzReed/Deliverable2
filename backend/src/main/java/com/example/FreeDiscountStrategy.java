package com.example;

public class FreeDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double cost) {
        return 0;
    }
}

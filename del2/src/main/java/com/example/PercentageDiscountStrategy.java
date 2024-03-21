package com.example;

public class PercentageDiscountStrategy implements DiscountStrategy{
    private final double percentage;

    public PercentageDiscountStrategy(double percentage){
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double cost){
        return cost * (1 - percentage/100);
    }
}

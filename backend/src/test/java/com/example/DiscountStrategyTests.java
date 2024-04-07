package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DiscountStrategyTests {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testApplyNoDiscount() {
        DiscountStrategy noDiscount = new NoDiscountStrategy();
        double originalPrice = 100.0;
        double discountedPrice = noDiscount.applyDiscount(originalPrice);
        assertEquals("Price should remain unchanged with NoDiscountStrategy", originalPrice, discountedPrice, 0.0);
    }

    @Test
    public void testApplyFreeDiscount() {
        DiscountStrategy freeDiscount = new FreeDiscountStrategy();
        double originalPrice = 100.0;
        double discountedPrice = freeDiscount.applyDiscount(originalPrice);
        assertEquals("Price should go to 0 with FreeDiscountStrategy", 0.0, discountedPrice, 0.0);
    }

    @Test
    public void testApplyPercentageDiscount() {
        DiscountStrategy tenPercentDiscount = new PercentageDiscountStrategy(10); // 10% discount
        double originalPrice = 100.0;
        double expectedDiscountedPrice = 90.0; // Expect 10% off
        double discountedPrice = tenPercentDiscount.applyDiscount(originalPrice);
        assertEquals("Discounted price should reflect 10% off", expectedDiscountedPrice, discountedPrice, 0.01);
    }

    @Test
    public void testApplyZeroPercentageDiscount() {
        DiscountStrategy zeroPercentDiscount = new PercentageDiscountStrategy(0); // 0% discount
        double originalPrice = 100.0;
        double discountedPrice = zeroPercentDiscount.applyDiscount(originalPrice);
        assertEquals("Price should remain unchanged with 0% discount", originalPrice, discountedPrice, 0.0);
    }

    @Test
    public void testApplyFiftyPercentageDiscount() {
        DiscountStrategy fiftyPercentDiscount = new PercentageDiscountStrategy(50); // 50% discount
        double originalPrice = 100.0;
        double expectedDiscountedPrice = 50.0; // Expect 50% off
        double discountedPrice = fiftyPercentDiscount.applyDiscount(originalPrice);
        assertEquals("Discounted price should reflect 50% off", expectedDiscountedPrice, discountedPrice, 0.01);
    }
}

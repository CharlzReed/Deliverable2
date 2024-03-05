package com.example;

public class Credit extends Payment {
    public Credit(int accountNumber, String pin, PaymentTypes payType, double remainingCred) {
        super(accountNumber, pin, PaymentTypes.CREDIT, remainingCred);
    }
}

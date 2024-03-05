package com.example;

public class Debit extends Payment {   
    public Debit(int accountNumber, String pin, PaymentTypes payType, double balance){
        super(accountNumber, pin, PaymentTypes.DEBIT, balance);
    }
}

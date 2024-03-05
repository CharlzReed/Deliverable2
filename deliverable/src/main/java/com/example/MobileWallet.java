package com.example;

public class MobileWallet extends Payment {

    public MobileWallet(int accountNumber, String pass, Payment payType, double balance) {
        super(accountNumber, pass, PaymentTypes.MOBILEWALLET, balance);
    }

}

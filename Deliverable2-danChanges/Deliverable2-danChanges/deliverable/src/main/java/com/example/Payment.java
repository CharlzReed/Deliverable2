package com.example;

public abstract class Payment {
    protected int accountID;
    protected String password;
    protected double balance;
    protected PaymentTypes payType;

    public Payment(int accountID, String pass, PaymentTypes payType, double bal) {
        this.accountID = accountID;
        this.password = pass;
        this.balance = bal;
        this.payType = payType;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double newBal) {
        this.balance = newBal;
    }

}

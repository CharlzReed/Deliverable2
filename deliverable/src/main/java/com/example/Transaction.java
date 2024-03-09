package com.example;

public class Transaction {
    protected String transactionMsg;
    protected Item item;
    protected Payment payMethod;

    public Transaction(Item item, Payment payMethod) {
        this.item = item;
        this.payMethod = payMethod;
    }

    public void procesPayment() {
        if (this.item.price < this.payMethod.balance) {
            this.item.setCopiesAvailable(this.item.copiesAvailable--);
            this.payMethod.setBalance(this.payMethod.balance - this.item.price);

            transactionMsg = "Your purchase has succeeded, your remaining balance is $" + this.payMethod.balance;
        }
    }

    public String getTransactionMsg() {
        return transactionMsg;
    }
}

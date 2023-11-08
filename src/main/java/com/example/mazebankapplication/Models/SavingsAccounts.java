package com.example.mazebankapplication.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingsAccounts extends Account{
    //The withdrawal limit from the savings
    private final DoubleProperty withdrawalLimit;

    public SavingsAccounts(String owner, String accountNumber, double balance, double withdrawalLimit) {
        super(owner, accountNumber, balance);
        this.withdrawalLimit = new SimpleDoubleProperty(this, "withdrawal limit", withdrawalLimit);
    }

    public DoubleProperty WithdrawalLimitProb() {return withdrawalLimit;}

    @Override
    public String toString(){
        return accountNumberProperty().get();
    }



}

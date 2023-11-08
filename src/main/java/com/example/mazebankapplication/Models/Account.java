package com.example.mazebankapplication.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Account {
    private final StringProperty Owner;
    private final StringProperty accountNumber;
    private final DoubleProperty balance;


    public Account(String owner, String accountNumber, double balance) {
        this.Owner = new SimpleStringProperty(this,"Owner", owner);
        this.accountNumber = new SimpleStringProperty(this,"AccountNumber", accountNumber);
        this.balance = new SimpleDoubleProperty(this, "Balance", balance);
    }

    public StringProperty ownerProperty() { return Owner;}
    public StringProperty accountNumberProperty() { return accountNumber;}
    public DoubleProperty balanceProperty(){ return balance;}
}

///////////////////////////mit lombock///////////////////////////////////////////////
//
//import lombok.Data;
//
//@Data
//public abstract class Account {
//    private final String owner;
//    private final String accountNumber;
//    private final double balance;
//
//    public Account(String owner, String accountNumber, double balance) {
//        this.owner = owner;
//        this.accountNumber = accountNumber;
//        this.balance = balance;
//    }
//}

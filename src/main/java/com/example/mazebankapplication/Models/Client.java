package com.example.mazebankapplication.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreate;


    public Client(String fName,String lName, String pAddress, Account cAccount, Account sAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(this, "FirstName", fName);
        this.lastName = new SimpleStringProperty(this, "LastName", lName);
        this.payeeAddress = new SimpleStringProperty(this, "PayeeAddress", pAddress);
        this.checkingAccount = new SimpleObjectProperty<>(this,"Checking Account", cAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this,"Savings Account", sAccount);
        this.dateCreate = new SimpleObjectProperty<>(this, "Date", date);
    }

    public StringProperty firstNameProperty(){ return firstName;}
    public StringProperty lastNameProperty(){ return lastName;}
    public StringProperty pAddressProperty(){ return payeeAddress;}
    public ObjectProperty<Account>checkingAccountProperty() { return checkingAccount;}
    public ObjectProperty<Account>savingsAccountProperty() { return savingsAccount;}
    public ObjectProperty<LocalDate>dateProperty() { return dateCreate;}
}



////////////////////////////////mit Lombock ////////////////////////////////////////////////////////////////////7
/*
        import javafx.beans.property.ObjectProperty;
        import javafx.beans.property.SimpleObjectProperty;
        import javafx.beans.property.SimpleStringProperty;
        import javafx.beans.property.StringProperty;
        import lombok.Data;

        import java.time.LocalDate;




@Data
public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress; // Corrected the variable name
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated; // Corrected the variable name

    public Client(String fName, String lName, String pAddress, Account cAccount, Account sAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.payeeAddress = new SimpleStringProperty(pAddress);
        this.checkingAccount = new SimpleObjectProperty<>(cAccount);
        this.savingsAccount = new SimpleObjectProperty<>(sAccount);
        this.dateCreated = new SimpleObjectProperty<>(date);
    }
}
*/


package com.example.mazebankapplication.Models;

import com.example.mazebankapplication.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Model extends Node {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;

    //Client Database
    private final Client client;
    private boolean clientLoginSuccessFlag;
    //Admin Database
    private boolean adminLoginSuccessFlag;
    private final ObservableList<Client> clients;


    private Model() {

        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        //Client Data Section
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);
        //Admin Data Section
        this.adminLoginSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }


    /*
    Client method Section
     */

    public boolean getClientLoginSuccessFlag() {
        return this.clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }

    public Client getClient() {
        return client;
    }

    public void evaluateClientCred(String pAddress, String password) {
        CheckingAccount checkingAccount;
        SavingsAccounts savingsAccounts;
        ResultSet resultSet = databaseDriver.getClientData(pAddress, password);

        try {
            if (resultSet != null && resultSet.isBeforeFirst()) {
                if (resultSet.next()) {
                    this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                    this.client.lastNameProperty().set(resultSet.getString("LastName"));
                    this.client.pAddressProperty().set(resultSet.getString("PayeeAddress"));
                    String[] dateParts = resultSet.getString("Date").split("-");
                    LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                    this.client.dateProperty().set(date);
                    checkingAccount = getCheckingAccount(pAddress);
                    savingsAccounts = getSavingsAccount(pAddress);
                    this.client.checkingAccountProperty().set(checkingAccount);
                    this.client.savingsAccountProperty().set(savingsAccounts);
                    this.clientLoginSuccessFlag = true;
                }
            } else {

                this.clientLoginSuccessFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Admin section
     */

    public boolean getAdminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag) {
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }

    public void evaluateAdminCred(String username, String password) {
        ResultSet resultSet = databaseDriver.getAdminData(username, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.adminLoginSuccessFlag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ein Fehler in evaluateAdminCred");
        }

    }

    public ObservableList<Client> getClients() {
        return clients;
    }


    public void setClients(){
        CheckingAccount checkingAccount;
        SavingsAccounts savingsAccounts;
        ResultSet resultSet = databaseDriver.getAllClientsData();
        try {
            while (resultSet.next()){
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt( dateParts[0] ), Integer.parseInt( dateParts[1] ), Integer.parseInt( dateParts[2] ) );
                checkingAccount = getCheckingAccount(pAddress);
                savingsAccounts = getSavingsAccount(pAddress);
                clients.add(new Client(fName,lName,pAddress,checkingAccount,savingsAccounts,date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("\u001B[34mEin Fehler ist aufgetreten. SQL-Exception: " + e.getMessage() + "\u001B[0m");
       }

    }

    public ObservableList<Client> searchClient(String pAddress) {
        ObservableList<Client> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchClient(pAddress);
        try {
            CheckingAccount checkingAccount = getCheckingAccount(pAddress);
            SavingsAccounts savingsAccounts = getSavingsAccount(pAddress);
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dateParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            searchResult.add(new Client(fName,lName,pAddress,checkingAccount,savingsAccounts,date));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\u001B[34mEin Fehler ist aufgetreten. SearchClient: " + e.getMessage() + "\u001B[0m");

        }
        return searchResult;

    }

    /*
    Utility Method Section

     */
    public CheckingAccount getCheckingAccount(String pAddress) {
        CheckingAccount account = null;
        ResultSet rs = databaseDriver.getCheckingAccountData(pAddress);
        try {

            if (rs.next()) {
                String num = rs.getString("AccountNumber");
                int tLimit = (int) rs.getDouble("TransactionLimit");
                double balance = rs.getDouble("Balance");
                account = new CheckingAccount(pAddress, num, balance, tLimit);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\u001B[34mEin Fehler ist aufgetreten. getCheckingAccount: " + e.getMessage() + "\u001B[0m");

//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        return account;
    }

    public SavingsAccounts getSavingsAccount(String pAddress) {
        SavingsAccounts account = null;
        ResultSet rs = databaseDriver.getSavingsAccountData(pAddress);
        try {
            if (rs.next()) {
                String num = rs.getString("AccountNumber");
                double wLimit = rs.getDouble("WithdrawalLimit");
                double balance = rs.getDouble("Balance");
                account = new SavingsAccounts(pAddress, num, balance, wLimit);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\u001B[34mEin Fehler ist aufgetreten. getSavingsAccount: " + e.getMessage() + "\u001B[0m");

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return account;
    }
}
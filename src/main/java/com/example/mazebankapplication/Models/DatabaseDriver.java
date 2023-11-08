package com.example.mazebankapplication.Models;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import io.github.cdimascio.dotenv.*;

public class DatabaseDriver {
    private Connection conn;


    public DatabaseDriver() {
        // Laden Sie die Umgebungsvariablen aus der .env-Datei
        Dotenv dotenv = Dotenv.configure()
                .directory("/home/fiedelbambu/Downloads/MazeBank Application/src/main/assets")
                .load();
        //  Dotenv dotenv = Dotenv.load();
        String dbUrl = dotenv.get("DB_URL");
        String dbUser = dotenv.get("DB_USER");
        String dbPassword = dotenv.get("DB_PASSWORD");

        try {
            this.conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

  /*
        Client section
         */

    public ResultSet getClientData(String pAddress, String password) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM Clients WHERE PayeeAddress = ? AND Password = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, pAddress);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            // Fehler behandeln
            e.printStackTrace();
            System.err.println("Fehler beim Erstellen des Clients: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Fehlercode: " + e.getErrorCode());
        }

        return resultSet;
    }

    /*
        Admin Section
         */
    public ResultSet getAdminData(String Username, String password) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM Admins WHERE Username = ? AND Password = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Erstellen AdminData: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Fehlercode: " + e.getErrorCode());
        }

        return resultSet;
    }

    public void createClient(String fName, String lName, String pAddress, String password, LocalDate date) {
        try {
            String dateStr = date.toString();
            String query = "INSERT INTO Clients (FirstName, LastName, PayeeAddress, Password, Date) VALUES ('" + fName + "', '" + lName + "', '" + pAddress + "', '" + password + "', '" + dateStr + "')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Erstellen des Clients: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Fehlercode: " + e.getErrorCode());
        }
    }


    public void createCheckingAccount(String owner, String number, double tLimit, double balance) {
        try {
            String query = "INSERT INTO CheckingAccounts (Owner, AccountNumber, TransactionLimit, Balance) VALUES ('" + owner + "', '" + number + "', " + tLimit + ", " + balance + ")";
            Statement statement = this.conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler bei createCheckingAccount: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Fehlercode: " + e.getErrorCode());
        }
    }




    public void createSavingsAccount(String owner, String number, double wLimit, double balance) {
        String generatedAccountNumber = null;
        Statement statement = null;

        try {
            statement = conn.createStatement();
            String query = "INSERT INTO SavingsAccounts (Owner, AccountNumber, WithdrawalLimit, Balance) VALUES ('" + owner + "', '" + number + "', " + wLimit + ", " + balance + ")";
            int rowsAffected = statement.executeUpdate(query);
            if (rowsAffected == 1) {
                generatedAccountNumber = number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Fehler bei creatSavingsAccount: " + e.getMessage());
                System.err.println("SQL State: " + e.getSQLState());
                System.err.println("SQL Fehlercode: " + e.getErrorCode());
            }
        }
    }

    public ResultSet getAllClientsData() {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients;");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in der Datenbank abfrage getAllClients");
        }
        return resultSet;
    }

    public ResultSet searchClient(String pAddress) {
        String query = "SELECT * FROM Clients WHERE PayeeAddress=?";
        try (Connection connection = this.conn;
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pAddress);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error while searching for a client", e);

        }
    }

    public void depositSavings(String pAddress, double amount) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("UPDATE SavingsAccounts SET Balance = '" + amount + "' WHERE Owner = '" + pAddress + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Erstellen des DepositSavings: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Fehlercode: " + e.getErrorCode());
        }
    }

    /*
        Utility Section
         */
    public int getLastClientsId() {
        Statement statement;
        ResultSet rs;
        int id = 0;
        try {
            statement = this.conn.createStatement();
            rs = statement.executeQuery("SELECT  * FROM  sqlite_sequence WHERE  name='Clients';");
            id = rs.getInt("seq");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Erstellen getLastClientsID: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Fehlercode: " + e.getErrorCode());
        }
        return id;
    }

    public ResultSet getCheckingAccountData(String PayeeAddress) {
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = this.conn.createStatement();
            String sql = "SELECT * FROM CheckingAccounts WHERE Owner = '" + PayeeAddress + "'";
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Erstellen des getCheckingAccountData: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Fehlercode: " + e.getErrorCode());
        }

        return rs;
    }


    public ResultSet getSavingsAccountData(String PayeeAddress) {
        PreparedStatement preparedStatement;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM SavingsAccounts WHERE Owner = ?";
            preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, PayeeAddress);

            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Erstellen des getSavingsAccount: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Fehlercode: " + e.getErrorCode());
        }

        return rs;
    }

}

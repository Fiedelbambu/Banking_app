package com.example.mazebankapplication.Views;

import com.example.mazebankapplication.Controllers.Admin.AdminController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class ViewFactory {
    private AccountType loginAccountType;
    //ClientView
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionView;
    private AnchorPane accountsView;


    // Admin Views
    private AnchorPane createClientView;
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane clientsView;
    private AnchorPane depositView;



    public ViewFactory(){
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem(){
        return clientSelectedMenuItem;
    }
    ////////////////////////////Client Views Section/////////////////////////////////////////////////////////
    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public  AnchorPane getTransactionView(){
        if (transactionView == null){
            try {
                URL resourceUrl = ClassLoader.getSystemResource("Transaction.fxml");
                if (resourceUrl != null){
                    transactionView = new FXMLLoader(resourceUrl).load();
                }else {
                    System.err.println("Resource 'Transaction.fxml' not found.");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionView;
    }

    public AnchorPane getAccountsView(){
        if (accountsView == null){
            try {
                URL resourceUrl = ClassLoader.getSystemResource("Accounts.fxml");
                if (resourceUrl != null) {
                    accountsView = new FXMLLoader(resourceUrl).load();
                } else {
                    System.err.println("Resource 'Clients.fxml' not found.");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    //////////////////////////////////////////////////////////


    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Client.fxml"));
        Stage stage =createStage(loader);
        stage.setWidth(1200);
        stage.setHeight(900);
    }
    /*
    Admin Views Section
     */
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){

        return adminSelectedMenuItem;
    }
    public AnchorPane getCreateClientView() {
        if (createClientView == null) {
            try {
                URL resourceUrl = ClassLoader.getSystemResource("CreateClient.fxml");
                if (resourceUrl != null) {
                    createClientView = new FXMLLoader(resourceUrl).load();
                } else {
                    System.err.println("Resource 'CreateClients.fxml' not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createClientView;
    }
    public AnchorPane getClientsView() {
        if (clientsView == null) {
            try {
//                clientsView = new FXMLLoader(getClass().getResource("/Clients.fxml")).load();
                URL resourceUrl = ClassLoader.getSystemResource("Clients.fxml");
                if (resourceUrl != null) {
                    clientsView = new FXMLLoader(resourceUrl).load();
                } else {
                    System.err.println("Resource 'Clients.fxml' not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientsView;
    }

    public AnchorPane getDepositView(){
        if (depositView == null) {
            try {
                URL resourceURL = ClassLoader.getSystemResource("Deposit.fxml");
                if (resourceURL != null) {
                    depositView = new FXMLLoader(resourceURL).load();
                } else {
                    System.err.println("Resource 'Deposit.fxml' not found");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin.fxml") );
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }
    private Stage createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
        stage.setResizable(false);
        stage.setTitle("Fiedels Bank");
        stage.show();
        return stage;
    }
    // Login schließen methode
    public void closeStage(Stage stage){
        stage.close();
    }
}
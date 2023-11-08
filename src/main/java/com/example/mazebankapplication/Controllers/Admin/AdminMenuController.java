package com.example.mazebankapplication.Controllers.Admin;

import com.example.mazebankapplication.Models.Model;
import com.example.mazebankapplication.Views.AdminMenuOptions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    // Deklaration der Schaltflächen für das Admin-Menü
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisierungsfunktion für das Admin-Menü-Controller

        // Füge Schaltflächen-Listener hinzu
        addListeners();


    }

    private void addListeners() {
        // Füge Listener für die verschiedenen Schaltflächen hinzu
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(event -> onClients());
        deposit_btn.setOnAction(event -> onDeposit());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onDeposit() {
        // Aktion beim Klicken auf "Deposit"-Schaltfläche
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DEPOSIT);
        System.out.println("onDeposit");
    }

    private void onCreateClient() {
        // Aktion beim Klicken auf "Create Client"-Schaltfläche
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENT);
        System.out.println("Create_Client");
    }

    private void onClients() {
        // Aktion beim Klicken auf "Clients"-Schaltfläche
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
        System.out.println("Clients");
    }

    private void onLogout() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        //set Admin Login Succes Flag
        Model.getInstance().setAdminLoginSuccessFlag(false);

    }

}

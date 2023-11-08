package com.example.mazebankapplication.Controllers.Admin;

import com.example.mazebankapplication.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    // Referenz auf das Haupt-Layout der Admin-Oberfläche
    public BorderPane admin_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialisierung des Admin-Controllers

        // Füge einen Listener hinzu, um auf Änderungen im ausgewählten Menüpunkt zu reagieren
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) ->{
            switch (newVal) {
                case CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                // Wenn "CLIENTS" ausgewählt ist, setze die Mitte des Layouts auf die Clients-Ansicht
                case DEPOSIT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDepositView());
                // Wenn "DEPOSIT" ausgewählt ist, setze die Mitte des Layouts auf die Einzahlungsansicht

                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
                // In allen anderen Fällen (Standardfall), setze die Mitte des Layouts auf die Ansicht zur Erstellung eines Clients
            }
        });
    }
}

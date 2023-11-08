package com.example.mazebankapplication.Controllers.Client;

import com.example.mazebankapplication.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {


    public BorderPane client_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null) {
                switch (newVal) {
                    case TRANSACTION:
                        client_parent.setCenter(Model.getInstance().getViewFactory().getTransactionView());
                        break;
                    case ACCOUNTS:
                        client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
                        break;
                    default:
                        client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
                        break;
                }
            } else {
                System.out.println("keine ausgabe!!!!!");
            }
        });
    }
}

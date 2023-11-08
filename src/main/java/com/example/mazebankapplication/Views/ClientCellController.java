package com.example.mazebankapplication.Views;

import com.example.mazebankapplication.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label name_lbl;
    public Label lName_lnl;
    public Label pAddress_lbl;
    public Label ch_acc_lbl;
    public Label sv_acc_lbl;
    public Label date_lbl;
    public Button delete_btn;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final Client client;

    public ClientCellController(Client client)  { this.client = client;}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            name_lbl.textProperty().bind(client.firstNameProperty());
            lName_lnl.textProperty().bind(client.lastNameProperty());
            pAddress_lbl.textProperty().bind(client.pAddressProperty());
            ch_acc_lbl.textProperty().bind(client.checkingAccountProperty().asString());
            sv_acc_lbl.textProperty().bind(client.savingsAccountProperty().asString());
            date_lbl.textProperty().bind(client.dateProperty().asString());

    }
}

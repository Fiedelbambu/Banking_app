package com.example.mazebankapplication.Controllers.Admin;

import com.example.mazebankapplication.Models.Client;
import com.example.mazebankapplication.Models.Model;
import com.example.mazebankapplication.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {


    public TextField pAddress_fld;
    public Button search_btn;
    public ListView<Client> resold_listview;
    public TextField amount_fld;
    public Button deposit_btn;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(event -> onClientSearch() );
        deposit_btn.setOnAction(event -> onDeposit() );
    }

    private void onClientSearch() {
        ObservableList<Client> searchResults = Model.getInstance().searchClient(pAddress_fld.getText());
        resold_listview.setCellFactory(e -> new ClientCellFactory());
        client = searchResults.get(0);
    }

    private void onDeposit() {
        double amount = Double.parseDouble(amount_fld.getText());
        double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();
        if (amount_fld.getText() != null){
            Model.getInstance().getDatabaseDriver().depositSavings(client.pAddressProperty().get(), newBalance);
        }
    }

    private void emptyFields() {
        pAddress_fld.setText("");
        amount_fld.setText("");
    }
}

package com.example.mazebankapplication.Views;

import com.example.mazebankapplication.Controllers.Admin.ClientCellController;
import com.example.mazebankapplication.Controllers.Client.TransactionCellController;
import com.example.mazebankapplication.Models.Client;
import com.example.mazebankapplication.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ClientCellFactory extends ListCell<Client> {

 @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client,empty);
        if (empty){
            setText(null);
            setGraphic(null);
           System.err.println("Fehler: Die Zelle ist leer");
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientCell.fxml"));
            ClientCellController controller = new ClientCellController(client);
            loader.setController(controller);
            setText(null);
        try {
            setGraphic(loader.load());
        } catch (IOException e) {
            System.err.println("Es ist ein Fehler aufgetreten: " + e.getMessage());
            e.printStackTrace();
            }
        }
    }
}

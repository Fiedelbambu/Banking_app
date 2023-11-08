package com.example.mazebankapplication.Controllers.Admin;

import com.example.mazebankapplication.Models.Client;
import com.example.mazebankapplication.Models.Model;
import com.example.mazebankapplication.Views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    // ListView zur Anzeige der Kunden
    public ListView<Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Daten initialisieren und der ListView zuweisen
        initData();
        clients_listview.setItems(Model.getInstance().getClients());
        // Verwende die CellFactory, um benutzerdefinierte Zellen für die ListView zu erstellen
        clients_listview.setCellFactory(e -> new ClientCellFactory());
    }

    private void initData() {
        // Initialisierung der Daten, wenn die Liste der Kunden leer ist
        if (Model.getInstance().getClients().isEmpty()) {
            Model.getInstance().setClients();
        }
    }
}

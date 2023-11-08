package com.example.mazebankapplication.Controllers.Admin;

import com.example.mazebankapplication.Models.Client;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

    public ClientCellController(Client client){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

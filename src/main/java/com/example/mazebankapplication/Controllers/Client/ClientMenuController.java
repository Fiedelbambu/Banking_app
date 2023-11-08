package com.example.mazebankapplication.Controllers.Client;

import com.example.mazebankapplication.Controllers.LoginController;
import com.example.mazebankapplication.Models.Model;
import com.example.mazebankapplication.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button Transaction_btn;
    public Button Accounts_btn;
    public Button Profile_btn;
    public Button Logout_btn;
    public Button report_btn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { addListeners(); }

           private void addListeners() {
            dashboard_btn.setOnAction(event -> onDashboard());
            Transaction_btn.setOnAction(event -> onTransaction());
            Accounts_btn.setOnAction(event -> onAccounts());
            Logout_btn.setOnAction(event -> onLogout());
        }


    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
        System.out.println("Accounts");
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
        System.out.println("Dashboard");
    }

    private void onTransaction() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTION);
        System.out.println("Transactions");
    }

    private void onLogout() {
        Stage stage = (Stage) Logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        //set Client Login Succes Flag
        Model.getInstance().setClientLoginSuccessFlag(false);

    }
}

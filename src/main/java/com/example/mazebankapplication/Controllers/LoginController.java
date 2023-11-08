package com.example.mazebankapplication.Controllers;

import com.example.mazebankapplication.Models.Model;
import com.example.mazebankapplication.Views.AccountType;
import com.example.mazebankapplication.Views.zweifaController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
///////////////////////////////////////////////////////////////////////////////////////////
public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;
    public Button zweiFa_btn;

    //////////////////////////////////////////////////////////////////////////////////////////7
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT,AccountType.ADMIN));
            acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        // Listener für die Auswahl des Kontotyps
            acc_selector.valueProperty().addListener(observable -> setAcc_selector());

            login_btn.setOnAction(event -> onLogin());

        // Füge einen KeyEvent-Handler hinzu
        password_fld.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onLogin();
            }
        });
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT) {
          //  Model.getInstance().getViewFactory().showClientWindow();
            // Client Login
            Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
            if (Model.getInstance().getClientLoginSuccessFlag()) {
                 Model.getInstance().getViewFactory().showClientWindow();
                //Close the Login Stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such Login Credentials");
            }
        } else {
            //Evaluate Admin Login Credentials
            Model.getInstance().evaluateAdminCred(payee_address_fld.getText(), password_fld.getText());
            if (Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                //Close Login
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such Login Credentials");
            }
        }
    }

//    private void onLogin() {
//        Stage stage = (Stage) error_lbl.getScene().getWindow();
//        boolean loginSuccess = false;
//
//        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT) {
//            // Client Login
//            Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
//            loginSuccess = Model.getInstance().getClientLoginSuccessFlag();
//        } else {
//            // Admin Login
//            Model.getInstance().evaluateAdminCred(payee_address_fld.getText(), password_fld.getText());
//            loginSuccess = Model.getInstance().getAdminLoginSuccessFlag();
//        }
//
//        if (loginSuccess) {
//            // Öffnen Sie die "zweiFa.fxml" Scene, wenn der Login erfolgreich ist
//            openTwoFaScene(stage);
//        } else {
//            payee_address_fld.setText("");
//            password_fld.setText("");
//            error_lbl.setText("No such Login Credentials");
//        }
//    }


//    private void openTwoFaScene(Stage currentStage) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/zweiFa.fxml"));
//            Parent root = loader.load();
//            Stage twoFaStage = new Stage();
//            twoFaStage.setScene(new Scene(root));
//
//            // Schließen Sie das aktuelle Login-Fenster
//            currentStage.close();
//
//            twoFaStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private void setAcc_selector(){
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        if (acc_selector.getValue() == AccountType.ADMIN){
            payee_address_lbl.setText("Username");
        }else {
            payee_address_lbl.setText("Payee Address:");
        }


    }
}

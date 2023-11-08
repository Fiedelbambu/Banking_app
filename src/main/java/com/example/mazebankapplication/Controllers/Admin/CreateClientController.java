package com.example.mazebankapplication.Controllers.Admin;

import com.example.mazebankapplication.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    // Textfelder für Benutzereingaben
    public TextField fName_fld;
    public TextField Name_fld;
    public TextField password_fld;
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField ch_ammount_fld;
    public CheckBox sv_acc_box;
    public TextField sv_ammount_fld;
    public Button create_client_btn;
    public Text error_lbl;
/////////////////////////////////////////////////////////////////////////////////////////////////////
    // Variablen zur Verwaltung der Benutzereingaben

    private String payeeAddress;
    private boolean createSavingsAccountFlag = false;


/////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Hinzufügen eines ActionListeners zur "Create Client"-Schaltfläche

        create_client_btn.setOnAction(actionEvent -> createClient());
        // Listener für die "Payee Address"-Checkbox

        pAddress_box.selectedProperty().addListener((observableValue, oldVal, newVal) ->{
            if (newVal) {
                payeeAddress = createPayeeAddress();
                OnCreatePayeeAddress();
            }
        } );
        // Listener für die "Checking Account"-Checkbox

        ch_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                createSavingsAccountFlag = true;
            }
        });
        // Listener für die "Savings Account"-Checkbox

        sv_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                createSavingsAccountFlag = true;
            }
        });
    }
    // Erstellen des Clients

    private void createClient(){
        //CreateCheckingAcc
        boolean createCheckingAccountFlag = false;
        if (createCheckingAccountFlag){
            createAccount("Checking");
        }
        // Erstellen des Savings-Kontos, falls ausgewählt

        if (createSavingsAccountFlag){
            createAccount("Savings");
        }
        //Create Client
        String fName = fName_fld.getText();
        String lName = Name_fld.getText();
        String password = password_fld.getText();
        // Passwort verschlüsseln
        String hashedPassword = hashPassword(password);
        // Client in der Datenbank erstellen

        Model.getInstance().getDatabaseDriver().createClient(fName, lName, payeeAddress, hashedPassword, LocalDate.now());

        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Client Created Successfully!");
        emptyFields();
    }
    ////////////////Passwort Verschlusseln mit Hash und Salt////////////////////////////////////////////////////////
    private String hashPassword(String password) {
        try {
            byte[] salt = generateSalt();
            String passwordWithSalt = password + new String(salt);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPasswordBytes = md.digest(passwordWithSalt.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPasswordBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Generierung von Salt

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

///////////////////////////////////// code-bLock/////////////////////////////////////////////////////////////////
    private void OnCreatePayeeAddress() {
        if (fName_fld.getText() != null & Name_fld.getText() != null) {
            pAddress_lbl.setText(payeeAddress);
        }
    }
        private void createAccount(String accountType) {
                double balance = Double.parseDouble(ch_ammount_fld.getText());
                // Generate Acc Number
            String firstSection = "3201";
            String lastSection = Integer.toString((new Random()).nextInt(9999) + 1000);
            String accountNumber = firstSection + " " + lastSection;
            // create the checking acc or savings acc
            if (accountType.equals("Checking")) {
                Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber, 10, balance);
            }else {
                Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress, accountNumber, 2000, balance);
            }
                }
        private String createPayeeAddress(){
            int id = Model.getInstance().getDatabaseDriver().getLastClientsId() +1;
            char fchar = Character.toLowerCase(fName_fld.getText().charAt(0));
            return "@"+fchar+Name_fld.getText()+id;
        }
    // Zurücksetzen der Benutzereingabefelder

    private  void emptyFields(){
        fName_fld.setText("");
        Name_fld.setText("");
        password_fld.setText("");
        pAddress_box.setSelected(false);
        pAddress_lbl.setText("");
        ch_acc_box.setSelected(false);
        ch_ammount_fld.setText("");
        sv_acc_box.setSelected(false);
    }
}

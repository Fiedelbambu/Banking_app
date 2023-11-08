package com.example.mazebankapplication.Views;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class zweifaController implements Initializable {
    public TextField codeOne_fld;
    public TextField codeTwo_fld;
    public TextField codeThree_fld;
    public TextField codeFour_fld;
    public TextField codeFive_fld;
    public TextField codeSix_fld;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // Verwenden Sie einen TextFormatter, um die Eingabe zu filtern
        TextFormatter<Object> numericTextFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("[0-9]*")) {
                return change;
            } else {
                return null;
            }
        });

        codeOne_fld.setTextFormatter(numericTextFormatter);
        codeTwo_fld.setTextFormatter(numericTextFormatter);
        codeThree_fld.setTextFormatter(numericTextFormatter);
        codeFour_fld.setTextFormatter(numericTextFormatter);
        codeFive_fld.setTextFormatter(numericTextFormatter);
        codeSix_fld.setTextFormatter(numericTextFormatter);

        codeOne_fld.setOnKeyReleased(event -> {
            if (codeOne_fld.getText().matches("\\d+")) {
                codeTwo_fld.requestFocus();
            }
            if (codeTwo_fld.getText().matches("\\d+")) {
                codeThree_fld.requestFocus();
            }
            if (codeThree_fld.getText().matches("\\d+")) {
                codeFour_fld.requestFocus();
            }
            if (codeFour_fld.getText().matches("\\d+")) {
                codeFive_fld.requestFocus();
            }
            if (codeFive_fld.getText().matches("\\d+")) {
                codeSix_fld.requestFocus();
            }
            if (codeSix_fld.getText().matches("\\d+")) {

            }
        });

    }
}

package com.example.mazebankapplication.Controllers.Client;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text user_name_tf;
    public Label login_date;
    public Label checking_bal;
    public Label checking_acc_num;
    public Label savings_bal;
    public Label savings_acc_num;
    public Label income_lbl;
    public Label expenses_lbl;
    public ListView transaction_listview;
    public TextField payee_fld;
    public TextField ammount_fld;
    public TextArea message_fld;
    public Button send_money_btn;
    public Label countdownLabel;
    public VBox user_name;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private int countdownTime = 5;
    private Timeline countdownTimer;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        startCountdownTimer();

        // Aktuelles Datum erhalten
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        java.util.Date date = new java.util.Date();
        String formattedDate = dateFormat.format(date);
        login_date.setText(formattedDate);

    }
//////////////////////////////Countdown Timer ///////////////////////////////////////////////////////////////////////
    private void startCountdownTimer() {
        countdownTimer = new Timeline(new KeyFrame(Duration.minutes(1), this::updateCountdown));
        countdownTimer.setCycleCount(countdownTime);
        countdownTimer.play();
    }
    private void updateCountdown(ActionEvent event) {
        countdownTime--;
        if (countdownTime > 0) {
            countdownLabel.setText("Automatische Abmeldung in " + countdownTime + " Minuten");
        } else {
            countdownLabel.setText("Sie wurden automatisch abgemeldet.");
            countdownTimer.stop();
            logoutUser();
        }
    }
    private void logoutUser() {
    }
}
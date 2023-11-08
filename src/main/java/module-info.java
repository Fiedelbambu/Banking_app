module com.example.mazebankapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires java.dotenv;


    opens com.example.mazebankapplication to javafx.fxml;
    exports com.example.mazebankapplication;
    exports com.example.mazebankapplication.Controllers;
    exports com.example.mazebankapplication.Controllers.Admin;
    exports com.example.mazebankapplication.Controllers.Client;
    exports com.example.mazebankapplication.Models;
    exports com.example.mazebankapplication.Views;

}
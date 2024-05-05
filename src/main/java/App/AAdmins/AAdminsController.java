package App.AAdmins;

import App.ADoctors.ADoctorsController;
import App.Welcome.WelcomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Main.Client;
import javafx.stage.Stage;
import App.AHome.AHomeController;

import java.io.IOException;

public class AAdminsController {
    @FXML
    private Button LogOutButton;
    private AHomeController ahomeController;
    public void setAHomeController(AHomeController aHomeController) {
        this.ahomeController = aHomeController;
    }

}

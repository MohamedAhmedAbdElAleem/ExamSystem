package App.AHome;

import App.AdminLogin.AdminLoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AHomeController {
    @FXML
    private Button AdminsButton;
    @FXML
    private Button LogOutButton;
    private String username1;
    @FXML
    private Label WelcomeLabel;
    private AdminLoginController adminLoginController;
    public void setAdminLoginController(AdminLoginController adminLoginController) {
        this.adminLoginController = adminLoginController;
    }
    public EventHandler<ActionEvent> LogOutButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AdminLogin/AdminLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AdminLoginController loginController = fxmlLoader.getController();
            loginController.setAHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
    }

    public void setUsername(String username1) {
        this.username1 = username1;
        WelcomeLabel.setText("Welcome "+username1);
    }
}

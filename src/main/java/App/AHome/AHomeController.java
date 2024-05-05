package App.AHome;

import App.AdminLogin.AdminLoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AHomeController {
    private String username1;
    @FXML
    private Label WelcomeLabel;
    private AdminLoginController adminLoginController;
    public void setAdminLoginController(AdminLoginController adminLoginController) {
        this.adminLoginController = adminLoginController;
    }

    public void setUsername(String username1) {
        this.username1 = username1;
        WelcomeLabel.setText("Welcome "+username1);
    }
}

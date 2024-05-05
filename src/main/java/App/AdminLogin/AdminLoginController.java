package App.AdminLogin;

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

public class AdminLoginController {
    private String Username;
    @FXML
    private TextField UID;
    @FXML
    private PasswordField UPassword;
    @FXML
    private Button LogInButton;
    @FXML
    private Button BackButton;
    public EventHandler<ActionEvent> LogInButtonClicked() {
        return e -> {
            String username = UID.getText();
            String password = UPassword.getText();
            // Send the username and password to the server
            Client client = new Client();
            client.sendMessage("login");
            client.sendMessage("Admin");
            client.sendMessage(username);
            client.sendMessage(password);
            String message = client.receiveMessage();
            if (message.equalsIgnoreCase("true")){
                String username1 = client.receiveMessage();
                Username = username1;
                System.out.println("Login Successful for user : "+username1);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AHome/AHome.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException ex) {
                    System.out.println("Error in loading scene : "+ex.getMessage());
                }
                AHomeController loginController = fxmlLoader.getController();
                loginController.setUsername(username1);
                loginController.setAdminLoginController(this);
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            // Display the message from the server
            System.out.println(message);
            client.close();
        };
    }
    public EventHandler<ActionEvent> BackButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/Welcome/Welcome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            WelcomeController loginController = fxmlLoader.getController();
            loginController.setLoginController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    @FXML
    public void initialize() {
        LogInButton.setOnAction(LogInButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
    }
    private WelcomeController welcomeController;
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }

    public String getUsername() {
        return Username;
    }
}

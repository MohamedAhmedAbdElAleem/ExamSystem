package App.AdminLogin;

import App.AAdmins.AAdminsController;
import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import App.Welcome.WelcomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
            String id = UID.getText();
            String password = UPassword.getText();
            // Send the username and password to the server
            Client client = new Client();
            client.sendMessage("login");
            client.sendMessage("Admin");
            client.sendMessage(id);
            client.sendMessage(password);
            String message = client.receiveMessage();
            if (message.equalsIgnoreCase("true")){
            String username1 = client.receiveMessage();
            Username = username1;

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SucessfulPopUp/SucessfulPopUp.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            SucessfulPopUpController sucessfulPopUpController = fxmlLoader.getController();
            sucessfulPopUpController.setSuccessfulMessage("Login Successful for user : "+username1);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // Correct usage of Modality
            stage.setScene(scene);
            stage.showAndWait();

            fxmlLoader = new FXMLLoader(getClass().getResource("/App/AHome/AHome.fxml")); // Reuse fxmlLoader
            try {
                scene = new Scene(fxmlLoader.load()); // Reuse scene
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AHomeController loginController = fxmlLoader.getController();
            loginController.setUsername(username1);
            loginController.setID(id);
            loginController.setAdminLoginController(this);
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow(); // Reuse stage
            stage.setScene(scene);
            stage.show();
        }
            else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ErrorPopUp/ErrorPopUp.fxml"));
                Parent parent = null;
                try {
                    parent = fxmlLoader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                ErrorPopUpController errorPopUpController = fxmlLoader.getController();
                errorPopUpController.setErrorMessage("Wrong ID or Password");


                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // This line makes the new window modal
                stage.setScene(scene);
                stage.showAndWait();
            }
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
    private AHomeController aHomeController;
    public void setAHomeController(AHomeController aHomeController) {
        this.aHomeController = aHomeController;
    }
    private ADoctorsController aDoctorsController;
    public void setAHomeController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }
    private AAdminsController aAdminsController;
    public void setAHomeController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    private ACoursesController aCoursesController;
    public void setAHomeController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }
}

package App.AAdmins;

import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.AdminLogin.AdminLoginController;
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
    private Button AHomeButton;
    @FXML
    private Button DoctorsButton;
    @FXML
    private Button CourseButton;
    @FXML
    private Button LogOutButton;
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
    public EventHandler<ActionEvent> CoursesButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.ACourses.ACoursesApplication.class.getResource("ACourses.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                // Get the AAdminsController instance
                ACoursesController aAdminsController = fxmlLoader.getController();
                aAdminsController.setAHomeController(this); // Pass reference to current controller
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> DoctorsButtonClicked() {
        return e -> {
            // Load the Doctors.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(App.ADoctors.ADoctorsApplication.class.getResource("ADoctors.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                // Get the ADoctorsController instance
                ADoctorsController aDoctorsController = fxmlLoader.getController();
                aDoctorsController.setAHomeController(this); // Pass reference to current controller
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            // Get the current stage
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        };
    }
    private EventHandler<ActionEvent> AHomeButtonClicked() {
        return e -> {
            // Load the Doctors.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(App.AHome.AHomeApplication.class.getResource("AHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                // Get the AHomeController instance
                AHomeController aHomeController = fxmlLoader.getController();
                aHomeController.setAHomeController(this); // Pass reference to current controller
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            // Get the current stage
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        };
    }
    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        CourseButton.setOnAction(CoursesButtonClicked());
        DoctorsButton.setOnAction(DoctorsButtonClicked());
        AHomeButton.setOnAction(AHomeButtonClicked());
    }
    private AHomeController ahomeController;
    public void setAHomeController(AHomeController aHomeController) {
        this.ahomeController = aHomeController;
    }

    private ACoursesController aCoursesController;
    public void setAHomeController(ADoctorsController aDoctorsController) {
        this.aCoursesController = aCoursesController;
    }

    private ADoctorsController aDoctorsController;
    public void setAHomeController(ACoursesController aCoursesController) {
        this.aDoctorsController = aDoctorsController;
    }
}

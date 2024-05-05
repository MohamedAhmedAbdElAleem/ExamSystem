package App.ACourses;

import App.AAdmins.AAdminsController;
import App.ADoctors.ADoctorsController;
import App.AHome.AHomeController;
import App.AddCourse.AddCourseController;
import App.AdminLogin.AdminLoginController;
import App.CID.CIDController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ACoursesController {
    @FXML
    private Button DeleteCourse;
    @FXML
    private Button AddCourse;
    @FXML
    private Button AHomeButton;
    @FXML
    private Button DoctorsButton;
    @FXML
    private Button AdminsButton;
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
    public EventHandler<ActionEvent> AdminsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.AAdmins.AAdminsApplication.class.getResource("AAdmins.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                // Get the AAdminsController instance
                AAdminsController aAdminsController = fxmlLoader.getController();
                aAdminsController.setUsername(username1);
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
                aDoctorsController.setUsername(username1);
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
                aHomeController.setUsername(username1);
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
        AddCourse.setOnAction(AddCourseButtonClicked());
        DoctorsButton.setOnAction(DoctorsButtonClicked());
        AdminsButton.setOnAction(AdminsButtonClicked());
        LogOutButton.setOnAction(LogOutButtonClicked());
        AHomeButton.setOnAction(AHomeButtonClicked());
        DeleteCourse.setOnAction(DeleteCourseButtonClicked());
    }

    private EventHandler<ActionEvent> DeleteCourseButtonClicked() {
        return e -> {
            // Load the AddCourse.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(App.CID.CIDApplication.class.getResource
                    ("CID.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                // Get the AddCourseController instance
                CIDController cidController = fxmlLoader.getController();
                cidController.setUsername(username1);
                cidController.setACoursesController(this); // Pass reference to current controller
            } catch (IOException ex) {
                System.out.println("Error in loading scene : " + ex.getMessage());
            }
            // Get the current stage
            Stage stage = new Stage();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        };
    }

    private EventHandler<ActionEvent> AddCourseButtonClicked() {
        return e -> {
            // Load the AddCourse.fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(App.AddCourse.AddCourseApplication.class.getResource
                    ("AddCourse.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                // Get the AddCourseController instance
                AddCourseController addCourseController = fxmlLoader.getController();
                addCourseController.setUsername(username1);
                addCourseController.setACoursesController(this); // Pass reference to current controller
            } catch (IOException ex) {
                System.out.println("Error in loading scene : " + ex.getMessage());
            }
            // Get the current stage
            Stage stage = new Stage();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        };
    }

    private AHomeController ahomeController;
    public void setAHomeController(AHomeController aHomeController) {
        this.ahomeController = aHomeController;
    }

    private AAdminsController aAdminsController;
    public void setAHomeController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    private ADoctorsController aDoctorsController;
    public void setAHomeController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }
    private String username1;
    public void setUsername(String username1) {
        this.username1 = username1;
    }
}

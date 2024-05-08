package App.AHome;

import App.ACourses.ACoursesController;
import App.AdminLogin.AdminLoginController;
import App.AAdmins.AAdminsController;
import App.ADoctors.ADoctorsController;
import App.AdminProfile.AdminProfileController;
import App.DoctorLogin.DoctorLoginController;
import App.Notification.NotificationController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AHomeController {

    @FXML
    private Button Notification;
    @FXML
    private Label AdminsNumber;
    @FXML
    private Label DoctorsNumber;
    @FXML
    private Label StudentsNumber;
    @FXML
    private Button CourseButton;
    @FXML
    private Button DoctorsButton;
    @FXML
    private Button AdminsButton;
    @FXML
    private Button LogOutButton;
    private String username1;
    @FXML
    private Label WelcomeLabel;
    @FXML
    private Button AdminProfile;
    private AdminLoginController adminLoginController;
    private ADoctorsController aDoctorsController;

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
    public EventHandler<ActionEvent> AdminsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.AAdmins.AAdminsApplication.class.getResource("AAdmins.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                // Get the AAdminsController instance
                AAdminsController aAdminsController = fxmlLoader.getController();
                aAdminsController.setUsername(username1);
                aAdminsController.setId(id);
                aAdminsController.setAHomeController(this); // Pass reference to current controller
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
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
                aAdminsController.setUsername(username1);
                aAdminsController.setId(id);
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
                aDoctorsController.setId(id);
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

    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        AdminsButton.setOnAction(AdminsButtonClicked());
        DoctorsButton.setOnAction(DoctorsButtonClicked());
        CourseButton.setOnAction(CoursesButtonClicked());
        Client client = new Client();
        client.sendMessage("getAdminDashBoardNumbers");
        String AdminsNumber = client.receiveMessage();
        String DoctorsNumber = client.receiveMessage();
        String StudentsNumber = client.receiveMessage();
        setAdminsNumber(AdminsNumber);
        setDoctorsNumber(DoctorsNumber);
        setStudentsNumber(StudentsNumber);
        AdminProfile.setOnAction(AdminProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());

        applyHoverEffect(CourseButton);
        applyHoverEffect(AdminsButton);
        applyHoverEffect(DoctorsButton);
        applyHoverEffect(LogOutButton);
    }


    private EventHandler<ActionEvent> NotificationButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/Notification/Notification.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            NotificationController loginController = fxmlLoader.getController();
//           //loginController.setUsername(Username);
            loginController.setAHomeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }
    private EventHandler<ActionEvent> AdminProfileButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.AdminProfile.AdminProfileController.class.getResource("AdminProfile.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                // Get the AAdminsController instance
                AdminProfileController adminProfileController = fxmlLoader.getController();
                adminProfileController.setUsername(username1);
                adminProfileController.setID(id);
                adminProfileController.setAHomeController(this); // Pass reference to current controller
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    public void setAdminsNumber(String adminsNumber) {

        AdminsNumber.setText(adminsNumber);
    }
    public void setDoctorsNumber(String doctorsNumber) {
        DoctorsNumber.setText(doctorsNumber);
    }
    public void setStudentsNumber(String studentsNumber) {
        StudentsNumber.setText(studentsNumber);
    }
    public void setUsername(String username1) {
        this.username1 = username1;
        WelcomeLabel.setText("Welcome "+username1);
    }
    private ACoursesController aCoursesController;
    public void setAHomeController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }

    private AAdminsController aAdminsController;
    public void setAHomeController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }
    public void setAHomeController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }
    private String id;
    public void setID(String id) {
        this.id = id;
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2e2e2e")); // Hover color
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #202020")); // Default color
    }

}

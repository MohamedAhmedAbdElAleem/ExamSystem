package App.ADoctors;

import App.AAdmins.AAdminsController;
import App.ACourses.ACoursesController;
import App.AHome.AHomeController;
import App.AddAdmin.AddAdminController;
import App.AddDoctor.AddDoctorController;
import App.AdminLogin.AdminLoginController;
import App.AdminProfile.AdminProfileController;
import App.DID.DIDController;
import Main.Admin;
import Main.Client;
import Main.Doctor;
import javafx.collections.ObservableList;
import App.Notification.NotificationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ADoctorsController {
    @FXML
    private Button Notification;
    @FXML
    private Button AdminProfile;
    @FXML
    private Button DeleteDoctor;
    @FXML
    private Button EditDoctor;
    @FXML
    private Button AddDoctor;
    @FXML
    private Button AHomeButton;
    @FXML
    private Button CourseButton;
    @FXML
    private Button AdminsButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private TableView<Doctor> DoctorsTable;
    @FXML
    private TableColumn<Doctor, String> IDColumn;
    @FXML
    private TableColumn<Doctor, String> NameColumn;
    @FXML
    private TableColumn<Doctor, String> SSNColumn;

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
    private EventHandler<ActionEvent> AddDoctorButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AddDoctor/AddDoctor.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                AddDoctorController addAdminsController = fxmlLoader.getController();
                addAdminsController.setAAdminsController(this);
                addAdminsController.setUsername(username1);
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            Stage stage = new Stage();
//                    (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
            ViewTableButtonClicked();
        };
    }
    private void ViewTableButtonClicked() {
        Client client = new Client();
        client.sendMessage("getDoctors");
        ObservableList<Doctor> doctors = null;
        doctors = client.getDoctors();
        DoctorsTable.setItems(doctors);
    }
    public void initialize() {
        AddDoctor.setOnAction(AddDoctorButtonClicked());
        EditDoctor.setOnAction(EditDeleteDoctorButtonClicked("Edit"));
        DeleteDoctor.setOnAction(EditDeleteDoctorButtonClicked("Delete"));
        LogOutButton.setOnAction(LogOutButtonClicked());
        CourseButton.setOnAction(CoursesButtonClicked());
        AdminsButton.setOnAction(AdminsButtonClicked());
        AHomeButton.setOnAction(AHomeButtonClicked());
        ViewTableButtonClicked();
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("Did"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Dname"));
        SSNColumn.setCellValueFactory(new PropertyValueFactory<>("Dssn"));
        AdminProfile.setOnAction(AdminProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
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
            loginController.setADoctorsController(this);
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
//                adminProfileController.setUsername(username1);
                adminProfileController.setADoctorsController(this); // Pass reference to current controller
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private EventHandler<ActionEvent> EditDeleteDoctorButtonClicked(String Process) {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DID/DID.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                DIDController didController = fxmlLoader.getController();
                didController.setUsername(username1);
                didController.setProcess(Process);
                didController.setADoctorsController(this);
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            Stage stage = new Stage();
//                    (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
            ViewTableButtonClicked();
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

    private ACoursesController aCoursesController;
    public void setAHomeController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }
    private String username1;
    public void setUsername(String username1) {
        this.username1 = username1;
    }
}

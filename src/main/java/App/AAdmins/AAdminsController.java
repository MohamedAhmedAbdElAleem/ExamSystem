package App.AAdmins;

import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.AID.AIDController;
import App.AddAdmin.AddAdminController;
import App.AdminLogin.AdminLoginController;
import App.Welcome.WelcomeController;
import Main.Admin;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Main.Client;
import javafx.stage.Modality;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import App.AHome.AHomeController;

import java.io.IOException;

public class AAdminsController {
    @FXML
    private Button AddAdmins;
    @FXML
    private Button EditAdmins;
    @FXML
    private Button DeleteAdmins;
    @FXML
    private Button AHomeButton;
    @FXML
    private Button DoctorsButton;
    @FXML
    private Button CourseButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private TableView<Admin> AdminsTable;
    @FXML
    private TableColumn<Admin, Integer> IDColumn;
    @FXML
    private TableColumn<Admin, String> NameColumn;

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
                aAdminsController.setUsername(username);
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
                aDoctorsController.setUsername(username);
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
                aHomeController.setUsername(username);
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
    public EventHandler<ActionEvent> EditDeleteAdminsButtonClicked(String Process) {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AID/AID.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                AIDController aidController = fxmlLoader.getController();
                aidController.setUsername(username);
                aidController.setProcess(Process);
                aidController.setAAdminsController(this);
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            Stage stage = new Stage();
//                    (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }
    public void initialize() throws IOException {
        AddAdmins.setOnAction(AddAdminsButtonClicked());
        EditAdmins.setOnAction(EditDeleteAdminsButtonClicked("Edit"));
        DeleteAdmins.setOnAction(EditDeleteAdminsButtonClicked("Delete"));
        LogOutButton.setOnAction(LogOutButtonClicked());
        CourseButton.setOnAction(CoursesButtonClicked());
        DoctorsButton.setOnAction(DoctorsButtonClicked());
        AHomeButton.setOnAction(AHomeButtonClicked());
        Client client = new Client();
        client.sendMessage("getAdmins");
        ObservableList<Admin> admins = client.getAdmins();
        AdminsTable.setItems(admins);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("Aid"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Aname"));
    }

    private EventHandler<ActionEvent> AddAdminsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AddAdmin/AddAdmin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                AddAdminController addAdminsController = fxmlLoader.getController();
                addAdminsController.setUsername(username);
                addAdminsController.setAAdminsController(this);
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            Stage stage = new Stage();
//                    (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
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
    private String username;
    public void setUsername(String username1) {
        this.username = username1;
    }
}

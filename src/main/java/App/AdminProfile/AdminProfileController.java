package App.AdminProfile;

import App.AAdmins.AAdminsController;
import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.AHome.AHomeController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminProfileController {
    @FXML
    private Button DoneButton;
    private AHomeController aHomeController;
    public void setAHomeController(AHomeController aHomeController) {
        this.aHomeController = aHomeController;
    }

    private  AAdminsController aAdminsController;
    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    private ADoctorsController aDoctorsController;
    public void setADoctorsController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }

    private ACoursesController aCoursesController;
    public void setACourseController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }

    public void initialize() {
        DoneButton.setOnAction(e -> {
            // Get the current stage
            Stage stage = (Stage) DoneButton.getScene().getWindow();
            // Close the stage
            stage.close();
        });
    }
    private String ID;
    public void setID(String id) {
        this.ID = id;
    }
    private String username1;
    public void setUsername(String username1) {
        this.username1 = username1;
    }
}

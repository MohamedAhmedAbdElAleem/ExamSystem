package App.ACourses;

import App.AAdmins.AAdminsController;
import App.AHome.AHomeController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ACoursesController {
    @FXML
    private Button LogOutButton;


    private AHomeController ahomeController;
    public void setAHomeController(AHomeController aHomeController) {
        this.ahomeController = aHomeController;
    }

    private AAdminsController aAdminsController;
    public void setAHomeController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }
}

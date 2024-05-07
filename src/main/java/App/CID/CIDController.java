package App.CID;

import App.ACourses.ACoursesController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import Main.Client;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CIDController {
    @FXML
    private TextField CID;
    @FXML
    private Button ProceedButton;

    private String username1;
    private ACoursesController aCoursesController;
    Validation validation = new Validation();
    public void initialize() {
        ProceedButton.setOnAction(proceedButtonClicked());
    }

    private EventHandler<ActionEvent> proceedButtonClicked() {
        return e -> {
            String id = CID.getText();
            Client client = new Client();
            client.sendMessage("checkCourseId");
            client.sendMessage(id);
            String response = client.receiveMessage();
            if (response.equalsIgnoreCase("true")){
                client.sendMessage("deleteCourse");
                client.sendMessage(id);
                response = client.receiveMessage();
                if (response.equalsIgnoreCase("true")){
                    validation.showSuccessPopUp("Course Deleted Successfully");
                }
                else {
                    validation.showErrorPopUp("Course ID Not Found");
                }
            }
            else {
                validation.showErrorPopUp("Course ID Not Found");
            }
        };

    }

    public void setUsername(String username1) {
        this.username1 = username1;
    }

    public void setACoursesController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }
}

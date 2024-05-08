package App.AssignStudent;

import App.AAdmins.AAdminsController;
import App.DStudent.DStudentController;
import App.EditAdmin.EditAdminController;
import Main.Client;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;

import java.io.IOException;

public class AssignStudentController {

    @FXML
    private TextField SID;
    @FXML
    private Button ProceedButton;
    public void initialize() {
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    Validation validation = new Validation();
    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            String sid = SID.getText();
            if(sid.isEmpty()){
                validation.showErrorPopUp("Student Not Found");
                return;
            }
            Client client = new Client();
            String responce = client.AssignStudent(sid,courseId);
            if (responce.equals("true")) {
                validation.showSuccessPopUp("Student Assigned Successfully");
            }else
            {
                validation.showErrorPopUp("Student Not Found or Already Assigned");
            }
        };
    }

    private DStudentController dStudentController;
    private String courseId;
    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

}

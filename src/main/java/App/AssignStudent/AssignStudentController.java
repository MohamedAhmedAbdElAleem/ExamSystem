package App.AssignStudent;

import App.AAdmins.AAdminsController;
import App.DStudent.DStudentController;
import App.EditAdmin.EditAdminController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AssignStudentController {


    private DStudentController dStudentController;
    private String courseId;
    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

}

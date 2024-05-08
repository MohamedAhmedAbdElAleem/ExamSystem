package App.SID;

import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.DStudent.DStudentController;
import App.EditStudent.EditStudentController;
import Main.Client;
import Main.Student;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.Buffer;

public class SIDController {

    @FXML
    private TextField SID;
    @FXML
    private Button ProceedButton;

    private String username;
    private String process;
    private String id;
    private DStudentController dStudentController;
    Validation validation = new Validation();
    public void initialize() {
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            String sid = SID.getText();

            if(sid.isEmpty()){
                validation.showErrorPopUp("Student Not Found");
                return;
            }
            if (process.equals("UnAssiqn")) {
                Client client = new Client();
                String responce = client.UnAssignStudent(sid,courseid);
                if (responce.equals("true")) {
                    validation.showSuccessPopUp("Student UnAssigned Successfully");
                }else
                {
                    validation.showErrorPopUp("Student Not Found");
                }
            }

            Client client = new Client();
            Student result = client.process("getStudentDetails",sid);
            if(result == null){
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EditStudent/EditStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            EditStudentController dStudentController = fxmlLoader.getController();
            dStudentController.setSIDController(this);
            dStudentController.setStudent(result);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        };
    }

    public void setUsername(String username1) {
        this.username = username1;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String courseid;
    public void setCourseId(String courseId) {
        this.courseid = courseId;
    }
}

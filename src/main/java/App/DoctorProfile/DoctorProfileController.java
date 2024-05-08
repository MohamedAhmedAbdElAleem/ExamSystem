package App.DoctorProfile;

import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DoctorProfileController {
    @FXML
    private Button DoneButton;
    private DHomeController dHomeController;
    private DExamController dExamController;
    @FXML
    private Label DoctorNameLabel;
    @FXML
    private Label DoctorIdLabel;
    @FXML
    private Label DoctorSsnLabel;

    private DBeforeController dBeforeController;

    private DStudentController dStudentController;
    public void setDHomeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }

    private DQABankController dqaBankController;
    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    public void setDQABankController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }

    public void initialize() {
        DoneButton.setOnAction(e -> {
            // Get the current stage
            Stage stage = (Stage) DoneButton.getScene().getWindow();
            // Close the stage
            stage.close();
        });
    }

    public void setDBeforeController(DBeforeController dBeforeController) {
        this.dBeforeController = dBeforeController;
    }
    private String username;
    public void setUsername(String username) {
        this.username = username;
        DoctorNameLabel.setText("Doctor Name : "+username);
    }
    private String id;
    public void setId(String id) {
        this.id = id;
        DoctorIdLabel.setText("Doctor ID : "+id);
    }
    private String ssn;
    public void setSsn(String ssn) {
        this.ssn = ssn;
        DoctorSsnLabel.setText("Doctor SSN : "+ssn);
    }
    private String courseid;
    public void setCourseId(String courseid) {
        this.courseid = courseid;
    }
}

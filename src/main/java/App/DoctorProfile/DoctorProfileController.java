package App.DoctorProfile;

import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DoctorProfileController {
    @FXML
    private Button DoneButton;
    private DHomeController dHomeController;
    private DExamController dExamController;

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
}

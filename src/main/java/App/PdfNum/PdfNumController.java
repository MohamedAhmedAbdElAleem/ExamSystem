package App.PdfNum;

import App.AAdmins.AAdminsController;
import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.AHome.AHomeController;
import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import App.ViewExamDoctor.ViewExamDoctorController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PdfNumController {
    @FXML
    private Button Download;
    @FXML
    private TextField PdfNum;

    private ViewExamDoctorController viewExamDoctorController;
    public void setPdfNumController(ViewExamDoctorController viewExamDoctorController) {
        this.viewExamDoctorController = viewExamDoctorController;
    }
}

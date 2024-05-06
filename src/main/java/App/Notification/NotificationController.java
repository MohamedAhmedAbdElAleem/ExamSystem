package App.Notification;

import App.AAdmins.AAdminsController;
import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.AHome.AHomeController;
import App.AddQuestion.AddQuestionController;
import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import App.EditQuestion.EditQuestionController;
import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotificationController {
    @FXML
    private Button DoneButton;
    private DHomeController dHomeController;
    private DStudentController dStudentController;
    private DQABankController dqaBankController;
    private DExamController dExamController;
    private SHomeController sHomeController;
    private SResultsController sResultsController;
    private SExamsController sExamsController;
    private AHomeController aHomeController;
    private AAdminsController aAdminsController;
    private ACoursesController aCoursesController;
    private ADoctorsController aDoctorsController;
    private DBeforeController dBeforeController;
    private SBeforeController sBeforeController;


    public void initialize() {
        DoneButton.setOnAction(e -> {
            // Get the current stage
            Stage stage = (Stage) DoneButton.getScene().getWindow();
            // Close the stage
            stage.close();
        });
    }

    public void setDHomeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }

    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    public void setDQABankController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }

    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }

    public void setSResultsController(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }

    public void setSExamsController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }

    public void setAHomeController(AHomeController aHomeController) {
        this.aHomeController = aHomeController;
    }

    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    public void setACoursesController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }

    public void setADoctorsController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }

    public void setDBeforeController(DBeforeController dBeforeController) {
        this.dBeforeController = dBeforeController;
    }

    public void setSBeforeController(SBeforeController sBeforeController) {
        this.sBeforeController = sBeforeController;
    }
}

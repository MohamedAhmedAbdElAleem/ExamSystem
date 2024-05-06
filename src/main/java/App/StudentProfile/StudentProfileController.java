package App.StudentProfile;

import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudentProfileController {
    @FXML
    private Button DoneButton;
    private SHomeController sHomeController;

    private SResultsController sResultsController;

    private SExamsController sExamsController;
    private SBeforeController sBeforeController;
    public void initialize() {
        DoneButton.setOnAction(e -> {
            // Get the current stage
            Stage stage = (Stage) DoneButton.getScene().getWindow();
            // Close the stage
            stage.close();
        });
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

    public void setSBeforeController(SBeforeController sBeforeController) {
        this.sBeforeController = sBeforeController;
    }
}

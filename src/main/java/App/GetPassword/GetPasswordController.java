package App.GetPassword;

import App.StudentLogin.StudentLoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GetPasswordController {
    @FXML
    private Button GetButton;

    public void initialize() {
        GetButton.setOnAction(e -> {
            // Get the current stage
            Stage stage = (Stage) GetButton.getScene().getWindow();
            // Close the stage
            stage.close();
        });
    }


    private StudentLoginController studentLoginController;
    public void setLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }
}

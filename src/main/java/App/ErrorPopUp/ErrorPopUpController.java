package App.ErrorPopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorPopUpController {
    @FXML
    private Button OkButton;
    @FXML
    private Label FailedLabel;

    public void setErrorMessage(String message) {
        FailedLabel.setText(message);
    }

    public void initialize() {
        OkButton.setOnAction(e -> {
            // Get the current stage
            Stage stage = (Stage) OkButton.getScene().getWindow();
            // Close the stage
            stage.close();
        });
    }

}

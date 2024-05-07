package App.ErrorPopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErrorPopUpController {
    @FXML
    private Button OkButton;

    public void initialize() {
        OkButton.setOnAction(e -> {
            // Get the current stage
            Stage stage = (Stage) OkButton.getScene().getWindow();
            // Close the stage
            stage.close();
        });
    }

}

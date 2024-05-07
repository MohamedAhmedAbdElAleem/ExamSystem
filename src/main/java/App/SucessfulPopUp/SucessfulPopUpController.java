package App.SucessfulPopUp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SucessfulPopUpController {
    @FXML
    private Button OkButton;

    @FXML
    private Label SuccessfulLabel;

    public void setSuccessfulMessage(String message) {
        SuccessfulLabel.setText(message);
    }

    public void initialize() {
        OkButton.setOnAction(e -> {
            Stage stage = (Stage) OkButton.getScene().getWindow();
            stage.close();
        });
    }

}

package App.AreYouSure;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class AreYouSureController {
    @FXML
    private Button YesButton;
    @FXML
    private Button NoButton;

    public void setOnYes(Runnable onYes) {
        YesButton.setOnAction(e -> onYes.run());
    }

    public void initialize() {
        NoButton.setOnAction(e -> ((Node) e.getSource()).getScene().getWindow().hide());
    }

}

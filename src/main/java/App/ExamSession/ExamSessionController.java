package App.ExamSession;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class ExamSessionController {
    @FXML
    private Button NextButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button SubmitButton;
    @FXML
    private Label QLabel;
    @FXML
    private RadioButton ARadio;
    @FXML
    private Label ALabel;
    @FXML
    private RadioButton BRadio;
    @FXML
    private Label BLabel;
    @FXML
    private RadioButton CRadio;
    @FXML
    private Label CLabel;
    @FXML
    private RadioButton DRadio;
    @FXML
    private Label DLabel;

    @FXML
    public void initialize() {
        ALabel.setOnMouseClicked(event -> ARadio.setSelected(true));
        BLabel.setOnMouseClicked(event -> BRadio.setSelected(true));
        CLabel.setOnMouseClicked(event -> CRadio.setSelected(true));
        DLabel.setOnMouseClicked(event -> DRadio.setSelected(true));
    }


}

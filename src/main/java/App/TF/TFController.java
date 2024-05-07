package App.TF;

import App.AddQuestion.AddQuestionController;
import App.EditQuestion.EditQuestionController;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class TFController {

    @FXML
    private RadioButton TrueButton;
    @FXML
    private RadioButton FalseButton;

    private final ToggleGroup group = new ToggleGroup();

    private AddQuestionController addQuestionController;
    private EditQuestionController editQuestionController;
    public void setAddQuestionController(AddQuestionController addQuestionController) {
        this.addQuestionController = addQuestionController;
    }

    public void setEditQuestionController(EditQuestionController editQuestionController) {
        this.editQuestionController = editQuestionController;
    }

    void initialize() {
        TrueButton.setToggleGroup(group);
        FalseButton.setToggleGroup(group);
    }
}

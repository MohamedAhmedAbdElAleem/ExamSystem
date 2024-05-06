package App.MCQ;

import App.AddQuestion.AddQuestionController;
import App.EditQuestion.EditQuestionController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MCQController {

    private AddQuestionController addQuestionController;
    private EditQuestionController editQuestionController;
    public void setAddQuestionController(AddQuestionController addQuestionController) {
        this.addQuestionController = addQuestionController;
    }

    public void setEditQuestionController(EditQuestionController editQuestionController) {
        this.editQuestionController = editQuestionController;
    }
}

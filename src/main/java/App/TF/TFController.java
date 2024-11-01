package App.TF;

import App.AddQuestion.AddQuestionController;
import App.EditQuestion.EditQuestionController;
import Main.Client;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class TFController {

    @FXML
    private RadioButton TrueButton;
    @FXML
    private RadioButton FalseRadio;
    @FXML
    private TextField Question;
    @FXML
    private TextField LectureNumber;
    @FXML
    private Button ProceedButton;

    Validation validation = new Validation();
    private final ToggleGroup group = new ToggleGroup();

    private AddQuestionController addQuestionController;
    private EditQuestionController editQuestionController;
    public void setAddQuestionController(AddQuestionController addQuestionController) {
        this.addQuestionController = addQuestionController;
    }

    public void setEditQuestionController(EditQuestionController editQuestionController) {
        this.editQuestionController = editQuestionController;
    }

    public void initialize() {
        TrueButton.setToggleGroup(group);
        FalseRadio.setToggleGroup(group);
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            String question = Question.getText();
            String lecture = LectureNumber.getText();
            String answer = "";
            if (TrueButton.isSelected()) {
                answer = "True";
            } else if (FalseRadio.isSelected()) {
                answer = "False";
            }
            if (question.isEmpty() || lecture.isEmpty() || answer.isEmpty()) {
                validation.showErrorPopUp("Please fill all the fields");
                return;
            }
            Client client = new Client();
            String response = client.addTFQuestion(question, difficultyLevel, lecture, answer, courseId);
            if (response.equalsIgnoreCase("true")) {
                validation.showSuccessPopUp("Question Added Successfully");
                Question.clear();
                LectureNumber.clear();
                group.selectToggle(null);
            }else {
                validation.showErrorPopUp("Error At The Inputs");
            }
        };
    }

    private String courseId;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    private String difficultyLevel;
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}

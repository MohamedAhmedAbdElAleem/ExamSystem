package App.MCQ;

import App.AddQuestion.AddQuestionController;
import App.EditQuestion.EditQuestionController;
import Main.Client;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MCQController {
    @FXML
    private TextField Question;
    @FXML
    private TextField LectureNumber;
    @FXML
    private TextField Ans;
    @FXML
    private TextField Opt2;
    @FXML
    private TextField Opt3;
    @FXML
    private TextField Opt4;
    @FXML
    private Button ProceedButton;

    Validation validation = new Validation();

    public void initialize() {
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            String question = Question.getText();
            String lecture = LectureNumber.getText();
            String answer = Ans.getText();
            String option2 = Opt2.getText();
            String option3 = Opt3.getText();
            String option4 = Opt4.getText();
            if (question.isEmpty() || lecture.isEmpty() || answer.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty()) {
                validation.showErrorPopUp("Please fill all the fields");
                return;
            }
            Client client = new Client();
            String response = client.addMCQQuestion(question, difficultyLevel, lecture, answer, option2, option3, option4, courseId);
            if (response.equalsIgnoreCase("true")) {
                validation.showSuccessPopUp("Question Added Successfully");
                Question.clear();
                LectureNumber.clear();
                Ans.clear();
                Opt2.clear();
                Opt3.clear();
                Opt4.clear();
            }
            else{
                validation.showErrorPopUp("Error At The Inputs");
            }
        };
    }

    private AddQuestionController addQuestionController;
    private EditQuestionController editQuestionController;
    public void setAddQuestionController(AddQuestionController addQuestionController) {
        this.addQuestionController = addQuestionController;
    }

    public void setEditQuestionController(EditQuestionController editQuestionController) {
        this.editQuestionController = editQuestionController;
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

package App.EditMCQ;

import App.QuestionID.QuestionIDController;
import Main.Client;
import Main.Question;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class EditMCQController {
    private String courseId;
    private String questionID;

    @FXML
    private TextField Question;
    @FXML
    private TextField Option2;
    @FXML
    private TextField Option3;
    @FXML
    private TextField Option4;
    @FXML
    private TextField CorrectOption;
    @FXML
    private TextField Lecture;
    @FXML
    private RadioButton Easy;
    @FXML
    private RadioButton Medium;
    @FXML
    private RadioButton Hard;
    @FXML
    private Button ProcessButton;


    Validation validation = new Validation();
    public void initialize() {
        ProcessButton.setOnAction(ProcessButtonClicked());
    }

    private EventHandler<ActionEvent> ProcessButtonClicked() {
        return e -> {
            String question = Question.getText();
            String option2 = Option2.getText();
            String option3 = Option3.getText();
            String option4 = Option4.getText();
            String correctOption = CorrectOption.getText();
            String lecture = Lecture.getText();
            String difficultyLevel = "";
            if(Easy.isSelected()) {
                difficultyLevel = "Easy";
            } else if(Medium.isSelected()) {
                difficultyLevel = "Medium";
            } else if(Hard.isSelected()) {
                difficultyLevel = "Hard";
            }
            else {
                validation.showErrorPopUp("Please Choose Difficulty Level");
                return;
            }
            if(question.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty() || correctOption.isEmpty() || lecture.isEmpty() || difficultyLevel.isEmpty()) {
                validation.showErrorPopUp("Please fill all the fields");
                return;
            }
            Client client = new Client();
            client.sendMessage("editMCQ");
            client.sendMessage(questionID);
            client.sendMessage(courseId);
            client.sendMessage(question);
            client.sendMessage(option2);
            client.sendMessage(option3);
            client.sendMessage(option4);
            client.sendMessage(correctOption);
            client.sendMessage(lecture);
            client.sendMessage(difficultyLevel);
            String response = client.receiveMessage();
            if(response.equalsIgnoreCase("true")) {
                validation.showSuccessPopUp("Question Edited Successfully");
                Question.clear();
                Option2.clear();
                Option3.clear();
                Option4.clear();
                CorrectOption.clear();
                Lecture.clear();
            } else {
                validation.showErrorPopUp("Error At The Inputs");
            }
        };
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    private QuestionIDController questionIDController;
    public void setEditQuestionController(QuestionIDController questionIDController) {
        this.questionIDController = questionIDController;
    }
    private Question question;
    public void setQuestion(Question question) {
        this.question = question;
        Question.setText(question.getQuestion());
        Option2.setText(question.getOption2());
        Option3.setText(question.getOption3());
        Option4.setText(question.getOption4());
        CorrectOption.setText(question.getAnswer());
        Lecture.setText(question.getLecture());
        if(question.getDifficultyLevel().equalsIgnoreCase("Easy")) {
            Easy.setSelected(true);
        } else if(question.getDifficultyLevel().equalsIgnoreCase("Medium")) {
            Medium.setSelected(true);
        } else if(question.getDifficultyLevel().equalsIgnoreCase("Hard")) {
            Hard.setSelected(true);
        }else {
            validation.showErrorPopUp("Error At The Inputs");
        }


    }
}

package App.EditTrueFalse;

import App.EditQuestion.EditQuestionController;
import App.QuestionID.QuestionIDController;
import Main.Client;
import Main.Question;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class EditTrueFalseController {
    @FXML
    private RadioButton TrueButton;
    @FXML
    private RadioButton FalseRadio;
    @FXML
    private TextField Question;
    @FXML
    private TextField Lecture;
    @FXML
    private RadioButton Easy;
    @FXML
    private RadioButton Medium;
    @FXML
    private RadioButton Hard;
    @FXML
    private Button ProceedButton;
    private String courseId;
    private String questionID;


    public void initialize() {
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            String question = Question.getText();
            String lecture = Lecture.getText();
            String answer = "";
            if(TrueButton.isSelected()) {
                answer = "True";
            } else if(FalseRadio.isSelected()) {
                answer = "False";
            }
            String difficultyLevel = "";
            if(Easy.isSelected()) {
                difficultyLevel = "Easy";
            } else if(Medium.isSelected()) {
                difficultyLevel = "Medium";
            } else if(Hard.isSelected()) {
                difficultyLevel = "Hard";
            }
            Client client = new Client();
            client.sendMessage("editTrueFalse");
            client.sendMessage(courseId);
            client.sendMessage(questionID);
            client.sendMessage(question);
            client.sendMessage(lecture);
            client.sendMessage(answer);
            client.sendMessage(difficultyLevel);
            String response = client.receiveMessage();
            if(response.equalsIgnoreCase("true")) {
                System.out.println("Question edited successfully");
            } else {
                System.out.println("Question not edited");
            }
    };
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    private Question question;
    public void setQuestion(Question question) {
        this.question = question;
        Question.setText(this.question.getQuestion());
        Lecture.setText(this.question.getLecture());
        if(this.question.getAnswer().equalsIgnoreCase("True")) {
            TrueButton.setSelected(true);
        } else if(this.question.getAnswer().equalsIgnoreCase("False")) {
            FalseRadio.setSelected(true);
        }
        if(this.question.getDifficultyLevel().equalsIgnoreCase("Easy")) {
            Easy.setSelected(true);
        } else if(this.question.getDifficultyLevel().equalsIgnoreCase("Medium")) {
            Medium.setSelected(true);
        } else if(this.question.getDifficultyLevel().equalsIgnoreCase("Hard")) {
            Hard.setSelected(true);
        }
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    private QuestionIDController questionIDController;
    public void setEditQuestionController(QuestionIDController questionIDController) {
        this.questionIDController = questionIDController;
    }
}

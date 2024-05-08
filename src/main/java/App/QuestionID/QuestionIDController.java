package App.QuestionID;

import App.DQABank.DQABankController;
import App.EditMCQ.EditMCQController;
import App.EditQuestion.EditQuestionController;
import App.EditTrueFalse.EditTrueFalseController;
import Main.Client;
import Main.Question;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class QuestionIDController {
    @FXML
    private Button ProceedButton;
    @FXML
    private TextField QID;

    private EditQuestionController editQuestionController;
    private DQABankController dqaBankController;
    public void setEditQuestionController(EditQuestionController editQuestionController) {
        this.editQuestionController = editQuestionController;
    }

    public void setDQABankController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    public void initialize() {
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            String questionID = QID.getText();
            Client client = new Client();
            client.sendMessage("checkQuestionID");
            client.sendMessage(questionID);
            client.sendMessage(courseId);
            client.sendMessage(questionType);
            String response = client.receiveMessage();
            if(response.equalsIgnoreCase("true")) {
                Question question = new Question();
                String Question = client.receiveMessage();
                String Lecture = client.receiveMessage();
                String QuestionType = client.receiveMessage();
                String CorrectOption = client.receiveMessage();
                String DifficultyLevel = client.receiveMessage();

                question.setQuestion(Question);
                question.setLecture(Lecture);
                question.setQuestionType(QuestionType);
                question.setAnswer(CorrectOption);
                question.setDifficultyLevel(DifficultyLevel);

                if(questionType.equalsIgnoreCase("MCQ")){
                    String Option2 = client.receiveMessage();
                    String Option3 = client.receiveMessage();
                    String Option4 = client.receiveMessage();
                    question.setOption2(Option2);
                    question.setOption3(Option3);
                    question.setOption4(Option4);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EditMCQ/EditMCQ.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException ex) {
                        System.out.println("Error in loading scene : "+ex.getMessage());
                    }
                    EditMCQController editMCQController = fxmlLoader.getController();
                    editMCQController.setQuestionID(questionID);
                    editMCQController.setQuestion(question);
                    editMCQController.setCourseId(courseId);
                    editMCQController.setEditQuestionController(this);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();


                } else if(questionType.equalsIgnoreCase("TF")){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EditTrueFalse/EditTrueFalse.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException ex) {
                        System.out.println("Error in loading scene : "+ex.getMessage());
                    }
                    EditTrueFalseController editMCQController = fxmlLoader.getController();
                    editMCQController.setEditQuestionController(this);
                    editMCQController.setQuestionID(questionID);
                    editMCQController.setQuestion(question);
                    editMCQController.setCourseId(courseId);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                }
            }
        };

    }

    String courseId;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    String questionType;
    public void setQuestionType(String type) {
        this.questionType = type;
    }
}

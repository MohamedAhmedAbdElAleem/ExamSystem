package App.AddQuestion;

import App.DQABank.DQABankController;
import App.MCQ.MCQController;
import App.TF.TFController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddQuestionController {

    @FXML
    private RadioButton Easy;
    @FXML
    private RadioButton Medium;
    @FXML
    private RadioButton Hard;
    private String difficultyLevel;

    private DQABankController dqaBankController;
    public void setDQABankController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    @FXML
    private Button ChosenMCQ;

    @FXML
    private Button ChosenTF;

    @FXML
    public void initialize() {
        ChosenMCQ.setOnAction(ChosenMCQButtonClicked());
        ChosenTF.setOnAction(ChosenTFButtonClicked());
    }

    private EventHandler<ActionEvent> ChosenTFButtonClicked() {
        return e -> {
            if (Easy.isSelected()) {
                difficultyLevel = "Easy";
            } else if (Medium.isSelected()) {
                difficultyLevel = "Medium";
            } else if (Hard.isSelected()) {
                difficultyLevel = "Hard";
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/TF/TF.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            TFController loginController = fxmlLoader.getController();
            loginController.setCourseId(courseId);
            loginController.setDifficultyLevel(difficultyLevel);
            System.out.println("Course ID: "+courseId);
            System.out.println("Difficulty Level: "+difficultyLevel);
            loginController.setAddQuestionController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }


    private EventHandler<ActionEvent> ChosenMCQButtonClicked() {
        return e -> {
            if (Easy.isSelected()) {
                difficultyLevel = "Easy";
            } else if (Medium.isSelected()) {
                difficultyLevel = "Medium";
            } else if (Hard.isSelected()) {
                difficultyLevel = "Hard";
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/MCQ/MCQ.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            MCQController loginController = fxmlLoader.getController();
            loginController.setCourseId(courseId);
            loginController.setDifficultyLevel(difficultyLevel);
            loginController.setAddQuestionController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }
    private String courseId;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}

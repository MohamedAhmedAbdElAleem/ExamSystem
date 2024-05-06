package App.EditQuestion;

import App.DQABank.DQABankController;
import App.EditMCQ.EditMCQController;
import App.EditTrueFalse.EditTrueFalseController;
import App.MCQ.MCQController;
import App.QuestionID.QuestionIDController;
import App.TF.TFController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditQuestionController {


    @FXML
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/QuestionID/QuestionID.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            QuestionIDController loginController = fxmlLoader.getController();
            loginController.setEditQuestionController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        };
    }


    private EventHandler<ActionEvent> ChosenMCQButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/QuestionID/QuestionID.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            QuestionIDController loginController = fxmlLoader.getController();
            loginController.setEditQuestionController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        };
    }
}

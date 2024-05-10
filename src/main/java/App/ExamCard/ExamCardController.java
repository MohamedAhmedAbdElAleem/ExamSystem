package App.ExamCard;

import App.DExam.DExamController;
import App.ExamFactor.ExamFactorController;
import Main.Exam;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ExamCardController {
    @FXML
    private VBox newQuizPane;
    @FXML
    private Label ExamCardLabel;
    @FXML
    private Button ExamCardButton;

    public void initialize() {
        ExamCardButton.setOnMouseClicked(addNewQuiz());
    }

    private EventHandler<? super MouseEvent> addNewQuiz() {
        return e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ExamFactor/ExamFactor.fxml"));
                ExamFactorController controller = new ExamFactorController();
                controller.setDExamController(dExamController);
                controller.setExamCardController(this);
                controller.setExam(quiz);
                fxmlLoader.setController(controller); // set the controller before loading
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                dExamController.ViewExams();
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
        };
    }
    private Exam quiz;
    public void setExam(Exam quiz) {
        this.quiz = quiz;
    }
    private DExamController dExamController;
    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
}

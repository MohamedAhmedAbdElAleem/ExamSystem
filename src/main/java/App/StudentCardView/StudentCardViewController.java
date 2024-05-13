package App.StudentCardView;

import App.DExam.DExamController;
import App.ExamFactor.ExamFactorController;
import App.SHome.SHomeController;
import App.ViewExamDoctor.ViewExamDoctorController;
import App.ViewExamStudent.ViewExamStudentController;
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

public class StudentCardViewController {
    @FXML
    private Button ViewExamButton;
    @FXML
    private Label ExamNameLabel;
    private Exam exam;
    public void setExam(Exam exam) {
        this.exam = exam;
        ExamNameLabel.setText(exam.getName());
    }
    private SHomeController sHomeController;
    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }
    public void initialize(){
        ViewExamButton.setOnMouseClicked(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ViewExamStudent/ViewExamStudent.fxml"));
                ViewExamStudentController controller = new ViewExamStudentController();
                controller.setSHomeController(sHomeController);
                controller.setExamCardController(this);
                controller.setExam(exam);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                sHomeController.ViewExams();
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
//            System.out.println(quiz.getExamId());
        });
    }
    private String examGrade;
    public void setExamGrade(String notGradedYet) {
        this.examGrade = notGradedYet;
        exam.setExamGrade(notGradedYet);
    }
}

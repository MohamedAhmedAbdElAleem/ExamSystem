package App.ExamView;

import App.DExam.DExamController;
import App.ExamFactor.ExamFactorController;
import App.SHome.SHomeController;
import App.ViewExamDoctor.ViewExamDoctorController;
import Main.Exam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ExamViewController {
    @FXML
    private Label ExamCardLabel;
    @FXML
    private Button ExamCardButton;
    public void initialize(){
        ExamCardButton.setOnMouseClicked(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ViewExamDoctor/ViewExamDoctor.fxml"));
                ViewExamDoctorController controller = new ViewExamDoctorController();
                controller.setDExamController(dExamController);
                controller.setExamCardController(this);
                controller.setExam(quiz);
                controller.setSelectedCourse(selectedCourse);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                dExamController.ViewExams();
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
//            System.out.println(quiz.getExamId());
        });
    }
    private Exam quiz;
    public void setExam(Exam quiz) {
        this.quiz = quiz;
        ExamCardLabel.setText(quiz.getName());
//        System.out.println(quiz.getExamId());
    }
    private DExamController dExamController;
    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
    private SHomeController sHomeController;
    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }
    private String selectedCourse;
    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}

package App.ExamView;

import App.DExam.DExamController;
import Main.Exam;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ExamViewController {
    @FXML
    private Label ExamCardLabel;
    @FXML
    private Button ExamCardButton;

    private Exam quiz;
    public void setExam(Exam quiz) {
        this.quiz = quiz;
        ExamCardLabel.setText(quiz.getName());
    }
    private DExamController dExamController;
    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
}

package App.StudentCardJoin;

import App.SExams.SExamsController;
import Main.Exam;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentCardJoinController {
    @FXML
    private Button JoinExamButton;
    @FXML
    private Label ExamNameLabel;
    private Exam exam;
    public void setExam(Exam exam) {
        this.exam = exam;
        ExamNameLabel.setText(exam.getName());
    }
    private SExamsController sExamsController;
    public void setSExamsController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }
    public void initialize() {
    }
}

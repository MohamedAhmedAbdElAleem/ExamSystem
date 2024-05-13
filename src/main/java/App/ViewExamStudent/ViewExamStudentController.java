package App.ViewExamStudent;

import App.SHome.SHomeController;
import App.StudentCardView.StudentCardViewController;
import Main.Exam;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ViewExamStudentController {
    @FXML
    private Label ExamDate;
    @FXML
    private Label ExamTime;
    @FXML
    private Label ExamResult;
    @FXML
    private Button DoneButton;
    private SHomeController sHomeController;
    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }
    private StudentCardViewController studentCardViewController;
    public void setExamCardController(StudentCardViewController studentCardViewController) {
        this.studentCardViewController = studentCardViewController;
    }
    private Exam exam;

    public void setExam(Exam exam) {
        this.exam = exam;
        Platform.runLater(() -> {
            ExamDate.setText(exam.getDate());
            ExamTime.setText(exam.getTime());
            ExamResult.setText(exam.getExamGrade());
        });
    }
    public void initialize() {
        DoneButton.setOnAction(DoneButtonClicked());
    }

    private EventHandler<ActionEvent> DoneButtonClicked() {
        return e -> {
            ((Button) e.getSource()).getScene().getWindow().hide();

        };
    }
}

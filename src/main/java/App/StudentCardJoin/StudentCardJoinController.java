package App.StudentCardJoin;

import App.ExamSession.ExamSessionController;
import App.SExams.SExamsController;
import Main.Exam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
        JoinExamButton.setOnAction(e->{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ExamSession/ExamSession.fxml"));
                ExamSessionController controller = new ExamSessionController();
                controller.setSExamsController(sExamsController);
                controller.setExamCardJoinController(this);
                controller.setExam(exam);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
        });
    }
}

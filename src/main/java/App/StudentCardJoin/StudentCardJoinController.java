package App.StudentCardJoin;

import App.ExamSession.ExamSessionController;
import App.SExams.SExamsController;
import Main.Client;
import Main.Exam;
import Main.Student;
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
                Client client = new Client();
                client.sendMessage("getStudentGradeInExam");
                client.sendMessage(String.valueOf(exam.getExamId()));
                client.sendMessage(String.valueOf(student.getSid()));
                String grade = client.receiveMessage();
                if(grade.equalsIgnoreCase("null")) {
                    String StudentAnswers = client.receiveMessage();
                    String ExamQuestions = client.receiveMessage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ExamSession/ExamSession.fxml"));
                    ExamSessionController controller = new ExamSessionController();
                    controller.setSExamsController(sExamsController);
                    controller.setExamCardJoinController(this);
                    controller.setStudent(student);
                    controller.setExam(exam);
                    controller.setStudentAnswers(StudentAnswers);
                    controller.setExamQuestions(ExamQuestions);
                    fxmlLoader.setController(controller);
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }else{
                    //alert that the student already ended the exam
                }
            } catch (Exception ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
        });
    }
    private Student student;
    public void setStudent(Student student) {
        this.student = student;
    }
}

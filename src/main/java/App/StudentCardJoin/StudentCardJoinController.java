package App.StudentCardJoin;

import App.ExamSession.ExamSessionController;
import App.SExams.SExamsController;
import Main.Client;
import Main.Exam;
import Main.Student;
import Main.Validation;
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
    Validation validation = new Validation();
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
//                    if(ExamQuestions != null && !ExamQuestions.equalsIgnoreCase("null")) {
//                        String[] questions = ExamQuestions.split(",");
//                        int[] parsedQuestions = new int[questions.length];
//                        for (int i = 0; i < questions.length; i++) {
//                            try {
//                                parsedQuestions[i] = Integer.parseInt(questions[i]);
//                            } catch (NumberFormatException ex) {
//                                System.out.println("Error in parsing string to integer: " + ex.getMessage());
//                            }
//                        }
//                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ExamSession/ExamSession.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    ExamSessionController controller = fxmlLoader.getController();
                    controller.setSExamsController(sExamsController);
                    controller.setExamCardJoinController(this);
                    controller.setStudent(student);
                    controller.setExam(exam);
                    controller.setStudentAnswers(StudentAnswers);
                    controller.setExamQuestions(ExamQuestions);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }else{
                    validation.showErrorPopUp("You have already taken this exam");
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

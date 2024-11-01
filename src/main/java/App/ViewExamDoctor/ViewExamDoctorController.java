package App.ViewExamDoctor;

import App.AreYouSure.AreYouSureController;
import App.DExam.DExamController;
import App.ExamView.ExamViewController;
import App.Notification.NotificationController;
import App.PdfNum.PdfNumController;
import Main.Client;
import Main.Exam;
import Main.Question;
import Main.Validation;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ViewExamDoctorController {
    Validation validation = new Validation();
    @FXML
    private Button DoneButton;
    @FXML
    private Button saveAsPDFButton;
    @FXML
    private TableView<Question> QuestionView;
    @FXML
    private TableColumn<Question, String> IDColumn;
    @FXML
    private TableColumn<Question, String> QuestionColumn;
    @FXML
    private TableColumn<Question, String> AnswerColumn;
    @FXML
    private TableColumn<Question, String> DifficultyColumn;
    @FXML
    private TableColumn<Question, String> LectureColumn;
    @FXML
    private TableColumn<Question, String> TypeColumn;
    @FXML
    private RadioButton True;
    @FXML
    private RadioButton False;
    @FXML
    private Button DownloadPDFButton;

    private ObservableList<Question> questions;
    private void ViewQuestions() {
        Client client = new Client();
        client.sendMessage("getQuestionsOfExam");
        String courseId = String.valueOf(quiz.getQbId());
        client.sendMessage(courseId);
        client.sendMessage(quiz.getQuestionsIds());
        questions = client.getQuestionsOfExam();
        QuestionView.setItems(questions);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("questionId"));
        QuestionColumn.setCellValueFactory(new PropertyValueFactory<>("Question"));
        AnswerColumn.setCellValueFactory(new PropertyValueFactory<>("Answer"));
        DifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficultyLevel"));
        LectureColumn.setCellValueFactory(new PropertyValueFactory<>("lecture"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("QuestionType"));
        DoneButton.setOnAction(DoneButtonClicked());
    }

    private EventHandler<ActionEvent> DoneButtonClicked() {
        return e -> {
            if (True.isSelected()||False.isSelected()){
                Client client = new Client();
                client.sendMessage("updateExam");
                client.sendMessage(String.valueOf(quiz.getExamId()));
                client.sendMessage(True.isSelected()?"true":"false");
                String response = client.receiveMessage();
                if(response.equalsIgnoreCase("true")) {
                    validation.showSuccessPopUp("Exam Updated Successfully");
                    Stage stage = (Stage) DoneButton.getScene().getWindow();
                    stage.close();
                } else {
                    validation.showErrorPopUp("Error in Updating Exam");
                    Stage stage = (Stage) DoneButton.getScene().getWindow();
                    stage.close();
                }
            } else {
                validation.showErrorPopUp("Please Select the Result Option");
            }
        };
    }

//    public void initialize() {
////        DownloadPDFButton.setOnAction(DownloadPDFButtonClicked());
//    }

    private EventHandler<ActionEvent> DownloadPDFButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/PdfNum/PdfNum.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            PdfNumController loginController = fxmlLoader.getController();
//           //loginController.setUsername(Username);
            loginController.setPdfNumController(this);
            loginController.setQuestions(questions);
            loginController.setExam(quiz);
            loginController.setCourse(selectedCourse);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
        };
    }

    public void initialize() {
        DownloadPDFButton.setOnAction(DownloadPDFButtonClicked());

    }


    private DExamController dExamController;
    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
    private ExamViewController examCardController;
    public void setExamCardController(ExamViewController examViewController) {
        this.examCardController = examViewController;
    }
    private Exam quiz;
    public void setExam(Exam quiz) {
        this.quiz = quiz;
        Platform.runLater(this::ViewQuestions);
    }
    private String selectedCourse;
    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}

package App.EligableStudent;

import App.AreYouSure.AreYouSureController;
import App.DExam.DExamController;
import App.ExamCard.ExamCardController;
import App.ExamFactor.ExamFactorController;
import Main.Client;
import Main.Exam;
import Main.Student;
import Main.Validation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;

public class EligableStudentController {
    @FXML
    private Button DeleteStudentButton;
    @FXML
    private Button MakeExamButton;
    @FXML
    private TableView<Student> StudentsTable;
    @FXML
    private TableColumn<Student, String> ID;
    @FXML
    private TableColumn<Student, String> Name;
    @FXML
    private TableColumn<Student, String> Email;
    @FXML
    private TableColumn<Student, String> RegistrationNumber;
    @FXML
    private RadioButton Yes;
    @FXML
    private RadioButton No;

    Validation validation = new Validation();

    public void initialize() {
        DeleteStudentButton.setOnAction(DeleteStudentButtonClicked());
        MakeExamButton.setOnAction(MakeExamButtonClicked());
        ID.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Sname"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Semail"));
        RegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("SregistrationNumber"));

    }

    private EventHandler<ActionEvent> MakeExamButtonClicked() {
        return e -> {
            if (!Yes.isSelected() && !No.isSelected()) {
                validation.showErrorPopUp("Please Select the Result Option");
            }
            else{
            FXMLLoader fxmlLoader = new FXMLLoader(App.AreYouSure.AreYouSureApplication.class.getResource("AreYouSure.fxml"));
            Scene scene = null;
            Stage stage = new Stage();
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AreYouSureController areYouSureController = fxmlLoader.getController();
            areYouSureController.setOnYes(MakeExam(e));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            }
        };
    }
    ObservableList<Student> students = FXCollections.observableArrayList();
    private void ViewStudentsOfCourse(){
        students = FXCollections.observableArrayList();
        Client client = new Client();
        students = client.ViewStudentsOfCourse(exam.getQbId());
        StudentsTable.setItems(students);
    }
    private Runnable MakeExam(ActionEvent e) {
        return new Runnable() {
            @Override
            public void run() {
                Client client = new Client();
                client.sendMessage("AddExam");
                client.sendExam(exam);
                if(No.isSelected()){
                    client.sendMessage("false");
                }
                else {
                    client.sendMessage("true");
                }

                for (Student student : StudentsTable.getItems()){
                    client.sendMessage(student.getSid());
                }
                client.sendMessage("end");
                String responce = client.receiveMessage();
                if (responce.equals("true")){
                    validation.showSuccessPopUp("Exam Created Successfully");

                    // Close the current stage (the EligableStudent page)
                    ((Node)(e.getSource())).getScene().getWindow().hide();
                }
                else {
                    validation.showErrorPopUp("Error in Creating Exam");
                }
            }
        };
    }

    private EventHandler<ActionEvent> DeleteStudentButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.AreYouSure.AreYouSureApplication.class.getResource("AreYouSure.fxml"));
            Scene scene = null;
            Stage stage = new Stage();
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AreYouSureController areYouSureController = fxmlLoader.getController();
            areYouSureController.setOnYes(DeleteStudent());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        };

    }

    private Runnable DeleteStudent() {
        return new Runnable() {
            @Override
            public void run() {
                Student selected = StudentsTable.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    validation.showErrorPopUp("Please Choose a student");
                } else {
                    students.remove(selected);
                    StudentsTable.getSelectionModel().clearSelection();
                    validation.showSuccessPopUp("Student Deleted Successfully");
                }
            }
        };
    }
    private DExamController dExamController;
    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
    private ExamCardController examCardController;
    public void setExamCardController(ExamCardController examCardController) {
        this.examCardController = examCardController;
    }
    private ExamFactorController examFactorController;
    public void setExamFactorController(ExamFactorController examFactorController) {
        this.examFactorController = examFactorController;
    }
    private Exam exam;
    public void setExam(Exam exam) {
        this.exam = exam;
        Platform.runLater(this::ViewStudentsOfCourse);
//        ViewStudentsOfCourse();
    }
}

package App.DQABank;

import App.AddQuestion.AddQuestionController;
import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DStudent.DStudentController;
import App.DoctorLogin.DoctorLoginController;
import App.DoctorProfile.DoctorProfileController;
import App.EditQuestion.EditQuestionController;
import App.Notification.NotificationController;
import App.QuestionID.QuestionIDController;
import Main.Client;
import Main.Question;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DQABankController {
    @FXML
    private Button DoctorProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button AddQuestion;
    @FXML
    private Button EditQuestion;
    @FXML
    private Button DHomeButton;
    @FXML
    private Button StudentsButton;
    @FXML
    private Button ExamsButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button DeleteQuestion;
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
    private TableColumn<Question, String> UsedColumn;
    @FXML
    private TableColumn<Question, String> TypeColumn;


    private EventHandler<ActionEvent> LogOutButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DoctorLogin/DoctorLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DoctorLoginController loginController = fxmlLoader.getController();
            loginController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> StudentsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DStudent/DStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DStudentController dStudentController = fxmlLoader.getController();
            dStudentController.setUsername(Username);
            dStudentController.setSsn(ssn);
            dStudentController.setId(id);
            dStudentController.setCourseId(courseId);
            dStudentController.setSelectedCourse(selectedCourse);
            dStudentController.setDHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        };
    }
    private EventHandler<ActionEvent> ExamsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DExam/DExam.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DExamController dExamController = fxmlLoader.getController();
            dExamController.setUsername(Username);
            dExamController.setSsn(ssn);
            dExamController.setId(id);
            dExamController.setCourseId(courseId);
            dExamController.setSelectedCourse(selectedCourse);
            dExamController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> BackButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DBefore/DBefore.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DBeforeController beforeController = fxmlLoader.getController();
            beforeController.setUsername(Username);
            beforeController.setSsn(ssn);
            beforeController.setId(id);
            beforeController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> DHomeButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DHome/DHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DHomeController dHomeController = fxmlLoader.getController();
            dHomeController.setUsername(Username);
            dHomeController.setSsn(ssn);
            dHomeController.setId(id);
            dHomeController.setCourseId(courseId);
            dHomeController.setSelectedCourse(selectedCourse);
            dHomeController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        };
    }
    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        StudentsButton.setOnAction(StudentsButtonClicked());
        ExamsButton.setOnAction(ExamsButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        DHomeButton.setOnAction(DHomeButtonClicked());
        AddQuestion.setOnAction(AddQuestionButtonClicked());
        EditQuestion.setOnAction(EditQuestionButtonClicked());
        DeleteQuestion.setOnAction(DeleteQuestionButtonClicked());
        DoctorProfile.setOnAction(DoctorProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
//        ViewQuestions();

    }

    private void ViewQuestions() {
        QuestionView.getItems().clear();
        Client client = new Client();
        client.sendMessage("getQuestions");
        client.sendMessage(courseId);
        ObservableList<Question> questions = client.getQuestions();
        QuestionView.setItems(questions);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("questionId"));
        QuestionColumn.setCellValueFactory(new PropertyValueFactory<>("Question"));
        AnswerColumn.setCellValueFactory(new PropertyValueFactory<>("Answer"));
        DifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficultyLevel"));
        LectureColumn.setCellValueFactory(new PropertyValueFactory<>("lecture"));
        UsedColumn.setCellValueFactory(new PropertyValueFactory<>("usedBefore"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("QuestionType"));
        applyHoverEffect(LogOutButton);
        applyHoverEffect(StudentsButton);
        applyHoverEffect(ExamsButton);
        applyHoverEffect(BackButton);
        applyHoverEffect(DHomeButton);

    }

    private EventHandler<ActionEvent> NotificationButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/Notification/Notification.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            NotificationController loginController = fxmlLoader.getController();
//           //loginController.setUsername(Username);
            loginController.setDQABankController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private EventHandler<ActionEvent> DoctorProfileButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DoctorProfile/DoctorProfile.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DoctorProfileController doctorProfileController = fxmlLoader.getController();
            doctorProfileController.setUsername(Username);
            doctorProfileController.setSsn(ssn);
            doctorProfileController.setId(id);
            doctorProfileController.setDQABankController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();

        };
    }



    private EventHandler<ActionEvent> DeleteQuestionButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/QuestionID/QuestionID.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            QuestionIDController loginController = fxmlLoader.getController();
            loginController.setDQABankController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
            ViewQuestions();
        };
    }

    private EventHandler<ActionEvent> EditQuestionButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EditQuestion/EditQuestion.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            EditQuestionController loginController = fxmlLoader.getController();
            loginController.setDQABankController(this);
            loginController.setCourseId(courseId);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
            ViewQuestions();
        };
    }

    private EventHandler<ActionEvent> AddQuestionButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AddQuestion/AddQuestion.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AddQuestionController loginController = fxmlLoader.getController();
            loginController.setDQABankController(this);
            loginController.setCourseId(courseId);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
            ViewQuestions();
        };
    }

    private DHomeController dHomeController;
    public void setDHomeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }
    private DStudentController dStudentController;
    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    private DExamController dExamController;
    public void setDStudentController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
    private String Username;
    public void setUsername(String username) {
        this.Username = username;
    }
    private String id;
    public void setId(String id) {
        this.id = id;
    }
    private String selectedCourse;
    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
    private String courseId;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
        ViewQuestions();
    }

    private String ssn;
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2e2e2e")); // Hover color
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #202020")); // Default color
    }
}

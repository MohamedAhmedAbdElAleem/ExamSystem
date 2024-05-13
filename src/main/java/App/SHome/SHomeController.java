package App.SHome;

import App.ExamView.ExamViewController;
import App.Notification.NotificationController;
import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SResults.SResultsController;
import App.StudentCardView.StudentCardViewController;
import App.StudentChangePassword.StudentChangePasswordController;
import App.StudentLogin.StudentLoginController;
import App.StudentProfile.StudentProfileController;
import Main.*;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;


public class SHomeController {
    @FXML
    private Button StudentProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button ChangePassword;
    @FXML
    private Button ResultButton;
    @FXML
    private Button ExamsButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private StudentLoginController studentLoginController;
    @FXML
    private Label StudentWelcome;
    @FXML
    private HBox PendingExams;
    @FXML
    private HBox CompletedExams;

    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        BackButton.setOnAction(BackbuttonClicked());
        ExamsButton.setOnAction(ExamsButtonClicked());
        ResultButton.setOnAction(ResultButtonClicked());
        StudentProfile.setOnAction(StudentProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
        ChangePassword.setOnAction(ChangePasswordButtonClicked());
        applyHoverEffect(LogOutButton);
        applyHoverEffect(BackButton);
        applyHoverEffect(ExamsButton);
        applyHoverEffect(ResultButton);
        applyHoverEffect(ChangePassword);
        Platform.runLater(() -> {
            ViewExams();
        });
    }
    private ObservableList<Results> results;
    private void ViewResults() {
        Client client = new Client();
        client.sendMessage("getResultsOfStudent");
        client.sendMessage(student.getSid());
        client.sendMessage(course.getCid());
        results = client.getResultsOfStudent();
        for (Results result : results) {
            if (result.getGrade() == null)
                result.setGrade("Not Graded Yet");
        }
    }

    public void ViewExams(){
        ViewResults();
        PendingExams.getChildren().clear();
        CompletedExams.getChildren().clear();
        ObservableList<Exam> exams = FXCollections.observableArrayList();
        Client client = new Client();
        exams = client.getExamsOfStudent(student.getSid());
        for (Exam exam : exams) {
            if (exam.getStartDate().isAfter(java.time.LocalDateTime.now())) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/StudentCardView/StudentCardView.fxml"));
                    VBox newQuizPane = loader.load();
                    StudentCardViewController examCardController = loader.getController();
                    examCardController.setExam(exam);
                    exam.setExamGrade("Not Graded Yet");
                    examCardController.setSHomeController(this);
                    PendingExams.getChildren().add(newQuizPane);
                } catch (IOException e) {
                    System.out.println("Error in loading scene : "+e.getMessage());
                }
            } else if(java.time.LocalDateTime.now().isAfter(exam.getStartDate().plusMinutes((long) (exam.getDuration()*60)))){                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/StudentCardView/StudentCardView.fxml"));
                    VBox newQuizPane = loader.load();
                    StudentCardViewController examCardController = loader.getController();
                    examCardController.setExam(exam);
                    for (Results result : results) {
                        if (result.getExamId().equalsIgnoreCase(String.valueOf(exam.getExamId()))) {
                            examCardController.setExamGrade(result.getGrade());
                        }
                    }
                    examCardController.setSHomeController(this);
                    CompletedExams.getChildren().add(newQuizPane);
                } catch (IOException e) {
                    System.out.println("Error in loading scene : "+e.getMessage());
                }
            }
        }
    }
    private EventHandler<ActionEvent> ChangePasswordButtonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/StudentChangePassword/StudentChangePassword.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            StudentChangePasswordController sChangePasswordController = fxmlLoader.getController();
            sChangePasswordController.setStudent(student);
            sChangePasswordController.setSHomeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
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
            loginController.setSHomeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private EventHandler<ActionEvent> StudentProfileButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/StudentProfile/StudentProfile.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            StudentProfileController loginController = fxmlLoader.getController();
            loginController.setStudent(student);
            loginController.setSHomeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }


    private EventHandler<ActionEvent> ResultButtonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SResults/SResults.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SResultsController sResultsController = fxmlLoader.getController();
            sResultsController.setStudent(student);
            sResultsController.setCourse(course);
            sResultsController.setSHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private EventHandler<ActionEvent> ExamsButtonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SExams/SExams.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SExamsController sExamsController = fxmlLoader.getController();
            sExamsController.setStudent(student);
            sExamsController.setCourse(course);
            sExamsController.setSHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private EventHandler<ActionEvent> BackbuttonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SBefore/SBefore.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SBeforeController sBeforeController = fxmlLoader.getController();
            sBeforeController.setStudent(student);
            sBeforeController.setStudentBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private EventHandler<ActionEvent> LogOutButtonClicked() {
        return e ->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/StudentLogin/StudentLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            StudentLoginController studentLoginController = fxmlLoader.getController();
            studentLoginController.setSBeforecontroller(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private SBeforeController sBeforeController;
    public void setDBeforeController(SBeforeController sBeforeController) {
        this.sBeforeController=sBeforeController;
    }
    private SResultsController sResultsController;
    public void setSResultsController(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }

    private SExamsController sExamsController;
    public void setSExamsController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }

    public void setStudentLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }
    private Student student;
    public void setStudent(Student student) {
        this.student = student;
        StudentWelcome.setText("Welcome "+student.getSname());
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2e2e2e")); // Hover color
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #202020")); // Default color
    }
    private Course course;
    public void setCourse(Course course) {
        this.course = course;
    }
}

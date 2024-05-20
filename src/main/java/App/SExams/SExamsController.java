package App.SExams;


import App.Notification.NotificationController;
import App.SBefore.SBeforeController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import App.StudentCardJoin.StudentCardJoinController;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SExamsController {
    @FXML
    private Button StudentProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button ChangePassword;
    @FXML
    private Button ResultButton;
    @FXML
    private Button SHomeButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private HBox PendingExamsPane;
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
            sBeforeController.setStudentLoginController(this);
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
    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        BackButton.setOnAction(BackbuttonClicked());
        SHomeButton.setOnAction(SHomeButtonClicked());
        ResultButton.setOnAction(ResultButtonClicked());
        StudentProfile.setOnAction(StudentProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
        ChangePassword.setOnAction(ChangePasswordButtonClicked());
        applyHoverEffect(LogOutButton);
        applyHoverEffect(BackButton);
        applyHoverEffect(SHomeButton);
        applyHoverEffect(ResultButton);
        applyHoverEffect(ChangePassword);
        Platform.runLater(() -> {
            ViewRunningExams();
        });
    }
    public void ViewRunningExams(){
        ObservableList<Exam> exams = FXCollections.observableArrayList();
        Client client = new Client();
        exams = client.getExamsOfStudent(student.getSid(), course.getCid());
        for (Exam exam : exams) {
            if (java.time.LocalDateTime.now().isAfter(exam.getStartDate()) &&
                    java.time.LocalDateTime.now().isBefore(exam.getStartDate().plusMinutes((long)(exam.getDuration() * 60.0)))) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/StudentCardJoin/StudentCardJoin.fxml"));
                    VBox newQuizPane = loader.load();
                    StudentCardJoinController examCardController = loader.getController();
                    examCardController.setExam(exam);
                    exam.setExamGrade("Not Graded Yet");
                    examCardController.setStudent(student);
                    examCardController.setSExamsController(this);
                    PendingExamsPane.getChildren().add(newQuizPane);
                } catch (IOException e) {
                    System.out.println("Error in loading scene : " + e.getMessage());
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
            sChangePasswordController.setSExamsController(this);
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
            loginController.setSExamsController(this);
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
            loginController.setSExamsController(this);
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

    private EventHandler<ActionEvent> SHomeButtonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SHome/SHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SHomeController sHomeController = fxmlLoader.getController();
            sHomeController.setStudent(student);
            sHomeController.setCourse(course);
            sHomeController.setSExamsController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private SHomeController sHomeController;
    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }

    private SResultsController sResultsController;
    public void setSHomeController(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }

    private Student student;
    public void setStudent(Student student) {
        this.student = student;

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

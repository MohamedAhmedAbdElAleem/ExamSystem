package App.SResults;


import App.Notification.NotificationController;
import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.StudentChangePassword.StudentChangePasswordController;
import App.StudentLogin.StudentLoginController;
import App.StudentProfile.StudentProfileController;
import Main.Student;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SResultsController {
    @FXML
    private Button StudentProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button ChangePassword;
    @FXML
    private Button ExamsButton;
    @FXML
    private Button SHomeButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
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
        ExamsButton.setOnAction(ExamsButtonClicked());
        SHomeButton.setOnAction(SHomeButtonClicked());
        StudentProfile.setOnAction(StudentProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
        ChangePassword.setOnAction(ChangePasswordButtonClicked());
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
            sHomeController.setSResultsController(this);
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
            sExamsController.setSHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
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
            sChangePasswordController.setSResultsController(this);
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
            loginController.setSResultsController(this);
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
            loginController.setSResultsController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }



    private SHomeController sHomeController;
    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }

    private SExamsController sExamsController;
    public void setSHomeController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }

    private Student student;
    public void setStudent(Student student) {
        this.student = student;
    }
}

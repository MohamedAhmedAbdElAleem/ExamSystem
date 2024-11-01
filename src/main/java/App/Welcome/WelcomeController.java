package App.Welcome;

import Main.HoverAnimation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import App.AdminLogin.AdminLoginController;
import App.DoctorLogin.DoctorLoginController;
import App.StudentLogin.StudentLoginController;
import javafx.util.Duration;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Button AdminButton;
    @FXML
    private Button DoctorButton;
    @FXML
    private Button StudentButton;
    @FXML
    private Label WelcomeLabel;
    private static final String TEXT_TO_TYPE = "Welcome, Choose your Role!";
    private static final Duration DELAY_BETWEEN_LETTERS = Duration.seconds(0.05);
    private int currentIndex = 0;

    HoverAnimation hoverAnimation = new HoverAnimation();

    public EventHandler<ActionEvent> AdminButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AdminLogin/AdminLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AdminLoginController loginController = fxmlLoader.getController();
            loginController.setWelcomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    public EventHandler<ActionEvent> DoctorButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DoctorLogin/DoctorLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DoctorLoginController loginController = fxmlLoader.getController();
            loginController.setWelcomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    public EventHandler<ActionEvent> StudentButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/StudentLogin/StudentLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            StudentLoginController loginController = fxmlLoader.getController();
            loginController.setWelcomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    public void initialize() {
        AdminButton.setOnAction(AdminButtonClicked());
        DoctorButton.setOnAction(DoctorButtonClicked());
        StudentButton.setOnAction(StudentButtonClicked());
        AdminButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, hoverAnimation);
        AdminButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, hoverAnimation);
        DoctorButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, hoverAnimation);
        DoctorButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, hoverAnimation);
        StudentButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, hoverAnimation);
        StudentButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, hoverAnimation);
        Platform.runLater(this::typeText);
    }

    public void typeText() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> typeNextLetter()),
                new KeyFrame(DELAY_BETWEEN_LETTERS)
        );
        timeline.setCycleCount(TEXT_TO_TYPE.length());
        timeline.play();

    }
    private void typeNextLetter() {
        if (currentIndex < TEXT_TO_TYPE.length()) {
            WelcomeLabel.setText(TEXT_TO_TYPE.substring(0, currentIndex + 1));
            currentIndex++;
        }
    }
    private AdminLoginController adminLoginController;

    public void setLoginController(AdminLoginController adminLoginController) {
        this.adminLoginController = adminLoginController;
    }
    private DoctorLoginController doctorLoginController;

    public void setLoginController(DoctorLoginController doctorLoginController) {
        this.doctorLoginController = doctorLoginController;
    }
    private StudentLoginController studentLoginController;

    public void setLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }
}

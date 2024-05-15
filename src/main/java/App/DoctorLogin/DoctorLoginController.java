package App.DoctorLogin;

import App.AHome.AHomeController;
import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SBefore.SBeforeController;
import App.StudentLogin.StudentLoginController;
import App.SucessfulPopUp.SucessfulPopUpController;
import App.Welcome.WelcomeController;
import Main.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import App.Welcome.WelcomeController;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class DoctorLoginController {
    private String Username;

    @FXML
    private TextField UID;
    @FXML
    private PasswordField UPassword;
    @FXML
    private Button LogInButton;
    @FXML
    private Button BackButton;
    @FXML
    private Label DoctorLabel;
    private static final String TEXT_TO_TYPE = "Doctor Login";
    private static final Duration DELAY_BETWEEN_LETTERS = Duration.seconds(0.05);
    private int currentIndex = 0;

    Validation validation = new Validation();
    HoverAnimation hoverAnimation = new HoverAnimation();

    Encryptor encryptor = new Encryptor();
    public EventHandler<ActionEvent> LogInButtonClicked() {
        return e -> {
            String id = UID.getText();
            String password = encryptor.funcEncrypt(UPassword.getText());
            DoctorLoginController controllerInstance = this;

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Client client = new Client();
                        client.sendMessage("login");
                        client.sendMessage("Doctor");
                        client.sendMessage(id);
                        client.sendMessage(password);
                        String message = client.receiveMessage();
                        if (message.equalsIgnoreCase("true")) {
                            String username1 = client.receiveMessage();
                            String SSN = client.receiveMessage();
                            Username = username1;
                            Platform.runLater(() -> {
                                validation.showSuccessPopUp("Doctor Login Successful For User : " + username1);
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DBefore/DBefore.fxml"));
                                Scene scene = null;
                                Stage stage = null;
                                try {
                                    scene = new Scene(fxmlLoader.load());
                                } catch (IOException ex) {
                                    System.out.println("Error in loading scene : " + ex.getMessage());
                                }
                                DBeforeController loginController = fxmlLoader.getController();
                                loginController.setUsername(Username);
                                loginController.setSsn(SSN);
                                loginController.setID(id);
                                loginController.setDoctorLoginController(controllerInstance);
                                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            });
                        } else {
                            Platform.runLater(() -> {
                                validation.showErrorPopUp("Invalid Username or Password");
                            });
                        }
                        client.close();
                    } catch (Exception ex) {
                        Platform.runLater(() -> {
                            validation.showErrorPopUp("Server is not Responding");
                        });
                    }
                    return null;
                }
            };

            new Thread(task).start();
        };
    }
    public EventHandler<ActionEvent> BackButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/Welcome/Welcome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            WelcomeController loginController = fxmlLoader.getController();
            loginController.setLoginController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    @FXML
    public void initialize() {
        LogInButton.setOnAction(LogInButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        LogInButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, hoverAnimation);
        LogInButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, hoverAnimation);
        BackButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, hoverAnimation);
        BackButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, hoverAnimation);
        typeText();
    }
    private WelcomeController welcomeController;
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }
    private DBeforeController dBeforeController;
    public void setDBeforeController(DBeforeController dBeforeController) {
        this.dBeforeController = dBeforeController;
    }

    private DHomeController dHomeController;
    public void setDBeforeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }

    private DStudentController dStudentController;
    public void setDBeforeController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    private DQABankController dqaBankController;
    public void setDBeforeController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    private DExamController dExamController;
    public void setDBeforeController(DExamController dExamController) {
        this.dExamController = dExamController;
    }

    private void typeText() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> typeNextLetter()),
                new KeyFrame(DELAY_BETWEEN_LETTERS)
        );
        timeline.setCycleCount(TEXT_TO_TYPE.length());
        timeline.play();
    }

    private void typeNextLetter() {
        if (currentIndex < TEXT_TO_TYPE.length()) {
            DoctorLabel.setText(TEXT_TO_TYPE.substring(0, currentIndex + 1));
            currentIndex++;
        }
    }
}

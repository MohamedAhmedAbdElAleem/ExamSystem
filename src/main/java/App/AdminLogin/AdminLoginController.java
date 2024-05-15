package App.AdminLogin;

import App.AAdmins.AAdminsController;
import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import App.Welcome.WelcomeController;
import Main.Encryptor;
import Main.HoverAnimation;
import Main.Validation;
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
import Main.Client;
import javafx.stage.Stage;
import App.AHome.AHomeController;
import javafx.util.Duration;

import java.io.IOException;

public class AdminLoginController {
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
    private Label AdminLabel;
    private static final String TEXT_TO_TYPE = "Admin Login";
    private static final Duration DELAY_BETWEEN_LETTERS = Duration.seconds(0.05);
    private int currentIndex = 0;
    HoverAnimation hoverAnimation = new HoverAnimation();
    Validation validation = new Validation();
    Encryptor encryptor = new Encryptor();
    public EventHandler<ActionEvent> LogInButtonClicked() {
        return e -> {
            String id = UID.getText();
            String password = encryptor.funcEncrypt(UPassword.getText());
            AdminLoginController controllerInstance = this;
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Client client = new Client();
                        client.sendMessage("login");
                        client.sendMessage("Admin");
                        client.sendMessage(id);
                        client.sendMessage(password);
                        String message = client.receiveMessage();
                        if (message.equalsIgnoreCase("true")) {
                            String username1 = client.receiveMessage();
                            Username = username1;
                            Platform.runLater(() -> {
                                validation.showSuccessPopUp("Admin Login Successful For User : " + username1);
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AHome/AHome.fxml"));
                                Scene scene = null;
                                Stage stage = null;
                                try {
                                    scene = new Scene(fxmlLoader.load());
                                } catch (IOException ex) {
                                    System.out.println("Error in loading scene : " + ex.getMessage());
                                }
                                AHomeController loginController = fxmlLoader.getController();
                                loginController.setUsername(username1);
                                loginController.setID(id);
                                loginController.setAdminLoginController(controllerInstance);
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
            AdminLabel.setText(TEXT_TO_TYPE.substring(0, currentIndex + 1));
            currentIndex++;
        }
    }

    private WelcomeController welcomeController;
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }

    public String getUsername() {
        return Username;
    }
    private AHomeController aHomeController;
    public void setAHomeController(AHomeController aHomeController) {
        this.aHomeController = aHomeController;
    }
    private ADoctorsController aDoctorsController;
    public void setAHomeController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }
    private AAdminsController aAdminsController;
    public void setAHomeController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    private ACoursesController aCoursesController;
    public void setAHomeController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }
}
